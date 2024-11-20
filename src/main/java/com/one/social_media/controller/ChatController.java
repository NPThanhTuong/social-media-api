package com.one.social_media.controller;


import com.one.social_media.dto.request.ChatNotification;
import com.one.social_media.dto.request.MessageReqDto;
import com.one.social_media.dto.response.MessageResDto;
import com.one.social_media.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
    MessageService messageService;
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageReqDto messageReqDto) {
        MessageResDto savedMsg = messageService.save(messageReqDto);

        messagingTemplate.convertAndSendToUser(
                messageReqDto.getRecipientId().toString(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRoomId(),
                        savedMsg.getContent()
                )
        );
    }

    @GetMapping("/api/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<MessageResDto>> findChatMessages(@PathVariable Long senderId,
                                                                @PathVariable Long recipientId) {
        return ResponseEntity
                .ok(messageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/api/messages/{chatRoomId}")
    public ResponseEntity<List<MessageResDto>> findChatMessages(@PathVariable Long chatRoomId) {
        return ResponseEntity
                .ok(messageService.findChatMessagesByRoomId(chatRoomId));
    }
}
