package com.one.social_media.service;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendShipService {
    UserRepository userRepository;

    UserMapper userMapper;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserResDto> getAllFriends() {
        var userId = getLoginUserId();
        Long relationshipTypeId = 1L;

        return userRepository.findAllFriends(userId, relationshipTypeId);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }

}
