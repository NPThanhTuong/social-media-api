package com.one.social_media.controller;

import com.one.social_media.dto.request.UpdateUserProfileDto;
import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Image;
import com.one.social_media.entity.User;
import com.one.social_media.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("info")
    public ResponseEntity<ApiResDto<UserResDto>> getInfo() {
        return ResponseEntity.ok(ApiResDto.<UserResDto>builder()
                .result(userService.getUserInfo())
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody @Valid UpdateUserProfileDto userProfileDto) {
        UserResDto updatedUser = userService.updateUserProfile(userProfileDto);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/images")
    public ResponseEntity<List<Image>> getUserImages() {
        List<Image> images = userService.getUserImages();
        return ResponseEntity.ok(images);  // Trả về danh sách hình ảnh
    }

}
