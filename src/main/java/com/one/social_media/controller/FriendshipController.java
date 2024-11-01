package com.one.social_media.controller;


import com.one.social_media.dto.response.FriendshipResponseDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.service.FriendShipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendshipController {
    FriendShipService friendShipService;

    @GetMapping("{userId}")
    public ResponseEntity<List<UserResDto>> getAllFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(friendShipService.getAllFriends(userId));
    }


    @GetMapping("/relationship/{userId}")
    public ResponseEntity<FriendshipResponseDto> getFriendshipDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(friendShipService.getFriendshipDetails(userId));
    }

    // Chấp nhận yêu cầu kết bạn
    @PostMapping("/accept/{userId}/{requesterId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable("userId") Long userId, @PathVariable("requesterId") Long requesterId) {
        friendShipService.acceptFriendRequest(userId, requesterId);
        return ResponseEntity.ok("Đã chấp nhận yêu cầu kết bạn.");
    }

    // Xóa bạn hoặc hủy yêu cầu
    @DeleteMapping("/remove/{userId}/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendShipService.removeFriendOrRequest(userId, friendId);
        return ResponseEntity.ok("Đã xóa bạn");
    }

    @DeleteMapping("/cancel/{userId}/{friendId}")
    public ResponseEntity<String> cancelFriendRequest(@PathVariable Long userId, @PathVariable Long friendId) {
        friendShipService.removeFriendOrRequest(userId, friendId);
        return ResponseEntity.ok("Đã hủy yêu cầu kết bạn");
    }

}
