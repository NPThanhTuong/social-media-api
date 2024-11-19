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

    @GetMapping
    public ResponseEntity<List<UserResDto>> getAllFriends() {
        return ResponseEntity.ok(friendShipService.getAllFriends());
    }


    @GetMapping("/relationship")
    public ResponseEntity<FriendshipResponseDto> getFriendshipDetails() {
        return ResponseEntity.ok(friendShipService.getFriendshipDetails());
    }

    // Chấp nhận yêu cầu kết bạn
    @PostMapping("/accept/{requesterId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable("requesterId") Long requesterId) {
        friendShipService.acceptFriendRequest(requesterId);
        return ResponseEntity.ok("Đã chấp nhận yêu cầu kết bạn.");
    }

    // Xóa bạn hoặc hủy yêu cầu
    @DeleteMapping("/remove/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long friendId) {
        friendShipService.removeFriendOrRequest(friendId);
        return ResponseEntity.ok("Đã xóa bạn");
    }

    @DeleteMapping("/cancel/{friendId}")
    public ResponseEntity<String> cancelFriendRequest(@PathVariable Long friendId) {
        friendShipService.removeFriendOrRequest(friendId);
        return ResponseEntity.ok("Đã hủy yêu cầu kết bạn");
    }


    @GetMapping("/suggestions")
    public ResponseEntity<List<UserResDto>> getSuggestedFriends() {
        List<UserResDto> suggestedFriends = friendShipService.getSuggestedFriends();
        return ResponseEntity.ok(suggestedFriends);
    }

    @PostMapping("/send-request/{receiverId}")
    public ResponseEntity<String> sendFriendRequest(@PathVariable("receiverId") Long receiverId) {
        friendShipService.sendFriendRequest(receiverId);
        return ResponseEntity.ok("Đã gửi yêu cầu kết bạn.");
    }

}
