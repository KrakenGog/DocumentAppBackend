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
