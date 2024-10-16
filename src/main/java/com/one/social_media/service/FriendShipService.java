package com.one.social_media.service;

import com.one.social_media.dto.response.FriendshipResponseDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Relationship;
import com.one.social_media.entity.User;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.RelationshipRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendShipService {
    UserRepository userRepository;
    RelationshipRepository relationshipRepository;
    UserMapper userMapper;

    public List<Relationship> getAllFriends(Long userId) {
        // 1 is friend in database

        // get user login.
        // var context = SecurityContextHolder.getContext();
        // String name = context.getAuthentication().getName();
//        return userRepository.findAllRelationShipByUserOwner(userId);

        return relationshipRepository.findAllByUserOwnerId(userId);
}

    public FriendshipResponseDto getFriendshipDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        // Lấy danh sách các mối quan hệ với từng relationshipType
        List<Relationship> friendsRelationships = relationshipRepository.findByUserOwnerIdAndRelationshipTypeId(userId, 1L);
        List<Relationship> requestsRelationships = relationshipRepository.findByUserOwnerIdAndRelationshipTypeId(userId, 2L);
        List<Relationship> requestsSentRelationships = relationshipRepository.findByUserOwnerIdAndRelationshipTypeId(userId, 3L);

        // Chuyển đổi từ Relationship sang User
        List<User> friends = friendsRelationships.stream()
                .map(Relationship::getUserReferenced) // lấy User từ relationship
                .collect(Collectors.toList());

        List<User> requests = requestsRelationships.stream()
                .map(Relationship::getUserReferenced)
                .collect(Collectors.toList());

        List<User> requestsSent = requestsSentRelationships.stream()
                .map(Relationship::getUserReferenced)
                .collect(Collectors.toList());

        return new FriendshipResponseDto(userMapper.toListUserResDto(friends),userMapper.toListUserResDto(requests) , userMapper.toListUserResDto(requestsSent));
    }


}
