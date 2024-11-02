package com.one.social_media.service;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.RoleRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    UserMapper userMapper;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserResDto getUserInfo() {
        var userId = getLoginUserId();

        var user = findUserById(userId);
        return userMapper.toUserResDto(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }
}