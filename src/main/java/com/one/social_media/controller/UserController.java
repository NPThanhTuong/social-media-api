package com.one.social_media.controller;

import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
