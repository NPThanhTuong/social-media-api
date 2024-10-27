package com.one.social_media.service;

import com.one.social_media.dto.request.IntrospectReqDto;
import com.one.social_media.dto.request.LoginReqDto;
import com.one.social_media.dto.request.RegisterReqDto;
import com.one.social_media.dto.response.IntrospectResDto;
import com.one.social_media.dto.response.JwtResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.User;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.RoleRepository;
import com.one.social_media.repository.UserRepository;
import com.one.social_media.security.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    JwtUtils jwtUtils;

    public IntrospectResDto validateToken(IntrospectReqDto introspectReqDto) {
        boolean isValid = true;

        try {
            isValid = jwtUtils.validateJwtToken(introspectReqDto.getToken());
        } catch (Exception e) {
            isValid = false;
        }

        return IntrospectResDto.builder()
                .isValid(isValid)
                .build();
    }

    public UserResDto registerUser(RegisterReqDto signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getPhone());

        user.setAvatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995552/no-avatar_nj3kwi.png");
        user.setCoverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995553/no-image_ow1ois.jpg");
        user.setName(signUpRequest.getName());

        var role = roleRepository.findByName("USER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRole(role);

        return userMapper.toUserResDto(userRepository.save(user));
    }

    public JwtResDto login(LoginReqDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResDto(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getAvatar(),
                roles.getFirst());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }
}
