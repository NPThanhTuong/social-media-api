package com.one.social_media.controller;


import com.one.social_media.dto.response.FriendshipResponseDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Relationship;
import com.one.social_media.service.FriendShipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendshipController {
    FriendShipService friendShipService;

    @GetMapping("{userId}")
    public ResponseEntity<List<Relationship>> getAllFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(friendShipService.getAllFriends(userId));
    }

    @GetMapping("/relationship/{userId}")
    public ResponseEntity<FriendshipResponseDto> getFriendshipDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(friendShipService.getFriendshipDetails(userId));
    }
}
