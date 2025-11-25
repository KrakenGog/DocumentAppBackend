package com.mse.dapp.service;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserStatusService {

    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();
    private final SimpMessagingTemplate messagingTemplate;

    public UserStatusService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if(headerAccessor.getUser() != null) {
            String username = headerAccessor.getUser().getName();
            onlineUsers.add(username);
            // Уведомляем всех о статусе
            messagingTemplate.convertAndSend("/topic/status", new StatusUpdate(username, true));
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if(headerAccessor.getUser() != null) {
            String username = headerAccessor.getUser().getName();
            onlineUsers.remove(username);
            messagingTemplate.convertAndSend("/topic/status", new StatusUpdate(username, false));
        }
    }

    public boolean isUserOnline(String username) {
        return onlineUsers.contains(username);
    }

    public record StatusUpdate(String username, boolean online) {}
}
