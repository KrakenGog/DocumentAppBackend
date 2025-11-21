package com.mse.dapp.config;

import com.mse.dapp.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtUtil jwtUtil;
    // UserDetailsService должен быть реализован (обычно это UserService)
    private final UserDetailsService userDetailsService;

    public WebSocketConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Префикс для исходящих сообщений от сервера к клиенту
        config.enableSimpleBroker("/topic");
        // Префикс для входящих сообщений (если будем использовать @MessageMapping)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Точка подключения: http://localhost:8080/ws
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Разрешаем запросы с фронтенда (CORS)
                .withSockJS(); // Включаем поддержку SockJS
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Interceptor для проверки JWT токена при WebSocket подключении
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String authorizationHeader = accessor.getFirstNativeHeader("Authorization");

                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        String token = authorizationHeader.substring(7);
                        try {
                            String username = jwtUtil.extractUsername(token);
                            if (username != null) {
                                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                                if (jwtUtil.validateToken(token, userDetails)) {
                                    UsernamePasswordAuthenticationToken auth =
                                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                    accessor.setUser(auth);
                                }
                            }
                        } catch (Exception e) {
                            // Логируем ошибку или игнорируем, соединение не установится без auth
                            System.out.println("WebSocket Auth Error: " + e.getMessage());
                        }
                    }
                }
                return message;
            }
        });
    }
}
