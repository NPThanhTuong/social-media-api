package com.one.social_media.controller;

import com.one.social_media.dto.request.ChatUserReqDto;
import com.one.social_media.dto.request.UpdateUserProfileDto;
import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Image;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @GetMapping("/api/users/info")
    public ResponseEntity<ApiResDto<UserResDto>> getInfo() {
        return ResponseEntity.ok(ApiResDto.<UserResDto>builder()
                .result(userService.getUserInfo())
                .build());
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<ApiResDto<UserResDto>> getInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(ApiResDto.<UserResDto>builder()
                .result(userService.getUserInfo(userId))
                .build());
    }

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public UserResDto addUser(
            @Payload ChatUserReqDto chatUserReqDto
    ) {
        var user = userService.connectUser(chatUserReqDto);

        return userMapper.toUserResDto(user);
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public UserResDto disconnectUser(
            @Payload ChatUserReqDto chatUserReqDto
    ) {
        var user = userService.disconnectUser(chatUserReqDto);

        return userMapper.toUserResDto(user);
    }

    @GetMapping("/api/users/connected")
    public ResponseEntity<ApiResDto<List<UserResDto>>> findConnectedUsers() {
        var users = userService.findConnectedUsers();

        return ResponseEntity.ok(ApiResDto.<List<UserResDto>>builder()
                .result(users)
                .build());
    }

    @PutMapping("/api/users/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody @Valid UpdateUserProfileDto userProfileDto) {
        UserResDto updatedUser = userService.updateUserProfile(userProfileDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/api/users/images")
    public ResponseEntity<List<Image>> getUserImages() {
        List<Image> images = userService.getUserImages();
        return ResponseEntity.ok(images);  // Trả về danh sách hình ảnh
    }

}
