package com.one.social_media.service;

import com.one.social_media.dto.request.MessageReqDto;
import com.one.social_media.dto.response.MessageResDto;
import com.one.social_media.entity.Message;
import com.one.social_media.mapper.MessageMapper;
import com.one.social_media.repository.MessageRepository;
import com.one.social_media.repository.RoomRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {
    MessageRepository messageRepository;
    RoomRepository roomRepository;
    UserRepository userRepository;
    RoomService roomService;
    MessageMapper messageMapper;

    public MessageResDto save(MessageReqDto messageReqDto) {
        var roomId = roomService
                .getChatRoomId(messageReqDto.getSenderId(), messageReqDto.getRecipientId(), true)
                .orElse(null);

        log.info("=== In msg service; save function ===");
        log.info("senderId: {}", messageReqDto.getSenderId());
        log.info("recipientId: {}", messageReqDto.getRecipientId());
        log.info("roomId = {}", roomId);

        var chatMessage = Message.builder()
                .content(messageReqDto.getContent())
                .sentAt(new Date())
                .room(roomRepository.getReferenceById(roomId))
                .sender(userRepository.getReferenceById(messageReqDto.getSenderId()))
                .build();

        messageRepository.save(chatMessage);

        return messageMapper.toMessageResDto(chatMessage);
    }

    public List<MessageResDto> findChatMessages(Long senderId, Long recipientId) {
        var roomId = roomService.getChatRoomId(senderId, recipientId, false);
        var messages = messageRepository.findByRoomId(roomId);

        return messageMapper.toListMessageResDto(messages);
    }

    public List<MessageResDto> findChatMessagesByRoomId(Long chatRoomId) {
        var messages = messageRepository.findByRoomId(Optional.ofNullable(chatRoomId));
        return messageMapper.toListMessageResDto(messages);
    }
}
