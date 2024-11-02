package com.one.social_media.controller;

import com.one.social_media.dto.request.IntrospectReqDto;
import com.one.social_media.dto.request.LoginReqDto;
import com.one.social_media.dto.request.RegisterReqDto;
import com.one.social_media.dto.response.ApiResDto;
import com.one.social_media.dto.response.IntrospectResDto;
import com.one.social_media.dto.response.JwtResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginReqDto loginRequest) {

        return ResponseEntity.ok(ApiResDto.<JwtResDto>builder()
                .result(authService.login(loginRequest))
                .build());
    }

    @PostMapping("signup")
    public ResponseEntity<ApiResDto<UserResDto>> registerUser(@Valid @RequestBody RegisterReqDto signUpRequest) {
        return ResponseEntity.ok(ApiResDto.<UserResDto>builder()
                .result(authService.registerUser(signUpRequest))
                .build());
    }

    @PostMapping("validate-token")
    public ResponseEntity<ApiResDto<IntrospectResDto>> validateToken(@RequestBody IntrospectReqDto introspectReqDto) {
        return ResponseEntity.ok(ApiResDto.<IntrospectResDto>builder()
                .result(authService.validateToken(introspectReqDto))
                .build());
    }

}
