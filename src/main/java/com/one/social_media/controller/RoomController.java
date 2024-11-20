package com.one.social_media.controller;

import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.RoomResDto;
import com.one.social_media.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {
    RoomService roomService;

    @GetMapping
    public ResponseEntity<ApiResDto<List<RoomResDto>>> getChatRooms() {
        return ResponseEntity.ok(ApiResDto.<List<RoomResDto>>builder()
                .result(roomService.getAllRooms())
                .build());
    }
}
