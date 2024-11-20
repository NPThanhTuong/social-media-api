package com.one.social_media.controller;

import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.MessageResDto;
import com.one.social_media.dto.response.RoomResDto;
import com.one.social_media.mapper.MessageMapper;
import com.one.social_media.repository.MessageRepository;
import com.one.social_media.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {
    MessageRepository messageRepository;
    MessageMapper messageMapper;

    RoomService roomService;

    @GetMapping("message-in-room/{roomId}")
    public ResponseEntity<ApiResDto<List<MessageResDto>>> gtMessageInRoom(@PathVariable("roomId") Long roomId) {
        var messages = messageRepository.findByRoomId(Optional.ofNullable(roomId));
        var result = messageMapper.toListMessageResDto(messages);

        return ResponseEntity.ok(ApiResDto.<List<MessageResDto>>builder()
                .result(result)
                .build());
    }

    @GetMapping("chatrooms")
    public ResponseEntity<ApiResDto<List<RoomResDto>>> getChatRooms() {
        return ResponseEntity.ok(ApiResDto.<List<RoomResDto>>builder()
                .result(roomService.getAllRooms())
                .build());
    }
}
