package com.mse.dapp.controller;

import com.mse.dapp.dto.*;
import com.mse.dapp.model.Message;
import com.mse.dapp.model.User;
import com.mse.dapp.repository.MessageRepo;
import com.mse.dapp.repository.UserRepo;
import com.mse.dapp.service.UserStatusService;
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
    private final UserStatusService userStatusService;

    public ChatController(MessageRepo messageRepo, UserRepo userRepo, 
                          SimpMessagingTemplate messagingTemplate, UserStatusService userStatusService) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.messagingTemplate = messagingTemplate;
        this.userStatusService = userStatusService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam(required = false) String recipient) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        List<Message> messages;

        if (recipient == null || recipient.isEmpty() || "General".equals(recipient)) {
            messages = messageRepo.findByRecipientIsNullOrderByCreatedAtAsc();
        } else {
            messages = messageRepo.findConversation(currentUser, recipient);
        }

        List<MessageDto> dtos = messages.stream()
                .map(m -> new MessageDto(m.getId(), m.getAuthor(), m.getRecipient(), m.getText(), m.getCreatedAt()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDto>> getContacts(@RequestParam(required = false) String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        List<User> allUsers = userRepo.findAll(); 
        
        if (search != null && !search.isBlank()) {
             allUsers = allUsers.stream()
                .filter(u -> u.getLogin().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
        }

        List<ContactDto> contacts = allUsers.stream()
                .filter(u -> !u.getLogin().equals(currentUser))
                .map(u -> {
                    boolean isOnline = userStatusService.isUserOnline(u.getLogin());
                    return new ContactDto(u.getLogin(), "", null, isOnline);
                })
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ChatPostResponse> sendMessage(@RequestBody ChatPostRequest request) {
        String text = request.getText();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sender = auth.getName();

        // Проверка наличия юзера
        userRepo.findByLogin(sender)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Message message = new Message();
        message.setAuthor(sender);
        message.setText(text);
        
        if (request.getRecipient() != null && !request.getRecipient().isEmpty() && !"General".equals(request.getRecipient())) {
            message.setRecipient(request.getRecipient());
        }

        message = messageRepo.save(message);
        
        MessageDto wsMsg = new MessageDto(message.getId(), message.getAuthor(), message.getRecipient(), message.getText(), message.getCreatedAt());

        if (message.getRecipient() == null) {
            // Broadcast
            messagingTemplate.convertAndSend("/topic/chat", wsMsg);
        } else {
            // Private
            messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", wsMsg);
            messagingTemplate.convertAndSendToUser(sender, "/queue/messages", wsMsg);
        }

        return ResponseEntity.ok(new ChatPostResponse(message.getId()));
    }
}
