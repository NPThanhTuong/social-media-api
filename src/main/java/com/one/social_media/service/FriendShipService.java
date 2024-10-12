package com.one.social_media.service;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendShipService {
    UserRepository userRepository;

    UserMapper userMapper;

    public List<UserResDto> getAllFriends(Long userId) {
        // 1 is friend in database

        // get user login.
        // var context = SecurityContextHolder.getContext();
        // String name = context.getAuthentication().getName();

        Long relationshipTypeId = 1L;

        return userRepository.findAllFriends(userId, relationshipTypeId);
    }

}
