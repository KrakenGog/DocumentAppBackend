#!/bin/bash

# Определяем базовый путь к пакетам
BASE_DIR="src/main/java/com/mse/dapp"

echo "--- Начало настройки бэкенда для Чата (WebSocket + REST) ---"

# 1. Создаем DTO для запросов и ответов, если их нет или они пустые
echo "Создание DTO..."

# ChatPostRequest (для приема { "text": "..." })
cat << 'EOF' > $BASE_DIR/dto/ChatPostRequest.java
package com.mse.dapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatPostRequest {
    private String text;
}
EOF

# ChatPostResponse (для ответа { "ur_id": 1 })
cat << 'EOF' > $BASE_DIR/dto/ChatPostResponse.java
package com.mse.dapp.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ChatPostResponse {
    private Long ur_id;
}
EOF

# 2. Обновляем Репозитории
echo "Обновление Репозиториев..."

# MessageRepo
cat << 'EOF' > $BASE_DIR/repository/MessageRepo.java
package com.mse.dapp.repository;

import com.mse.dapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    // Найти все сообщения, ID которых больше указанного (для подгрузки новых/истории)
    List<Message> findByIdGreaterThan(Long id);
}
EOF

# UserRepo (добавляем поиск по логину, если не было)
cat << 'EOF' > $BASE_DIR/repository/UserRepo.java
package com.mse.dapp.repository;

import com.mse.dapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
EOF

# 3. Создаем Конфигурацию WebSocket
echo "Создание WebSocketConfig.java..."

cat << 'EOF' > $BASE_DIR/config/WebSocketConfig.java
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
EOF

# 4. Создаем Контроллер Чата
echo "Создание ChatController.java..."

cat << 'EOF' > $BASE_DIR/controller/ChatController.java
package com.mse.dapp.controller;

import com.mse.dapp.dto.ChatPostRequest;
import com.mse.dapp.dto.ChatPostResponse;
import com.mse.dapp.dto.MessageDto;
import com.mse.dapp.dto.WsPayloads;
import com.mse.dapp.model.Message;
import com.mse.dapp.model.User;
import com.mse.dapp.repository.MessageRepo;
import com.mse.dapp.repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(MessageRepo messageRepo, UserRepo userRepo, SimpMessagingTemplate messagingTemplate) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Получение истории сообщений.
     * GET /chat?from_id=1
     */
    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam(name = "from_id", defaultValue = "0") Long fromId) {
        List<Message> messages = messageRepo.findByIdGreaterThan(fromId);

        List<MessageDto> dtos = messages.stream()
                .map(m -> new MessageDto(m.getId(), m.getAuthor(), m.getText()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    /**
     * Отправка сообщения (REST + WebSocket Broadcast).
     * POST /chat
     * Body: { "text": "Hello" }
     * Header: Authorization: Bearer ...
     */
    @PostMapping
    public ResponseEntity<ChatPostResponse> sendMessage(@RequestBody ChatPostRequest request) {
        String text = request.getText();
        if (text == null || text.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Получаем текущего пользователя из контекста безопасности (установленного через JWT)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        
        // Проверяем пользователя в БД (опционально, но надежно)
        User user = userRepo.findByLogin(login)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));

        // Сохраняем сообщение
        Message message = new Message();
        message.setAuthor(user.getLogin()); 
        message.setText(text);
        message = messageRepo.save(message);

        // Рассылаем через WebSocket всем в топик /topic/chat
        WsPayloads.ServerMessage wsMsg = new WsPayloads.ServerMessage(
                message.getId(),
                message.getAuthor(),
                message.getText()
        );
        messagingTemplate.convertAndSend("/topic/chat", wsMsg);

        // Возвращаем ID сообщения
        return ResponseEntity.ok(new ChatPostResponse(message.getId()));
    }
}
EOF

echo "--- Файлы успешно созданы! ---"
echo "Не забудь проверить, что твой UserService реализует интерфейс UserDetailsService,"
echo "или создай @Bean UserDetailsService, чтобы WebSocketConfig мог загружать пользователей."