package com.one.social_media.service;

import com.one.social_media.dto.response.FriendshipResponseDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Relationship;
import com.one.social_media.entity.RelationshipKey;
import com.one.social_media.entity.RelationshipType;
import com.one.social_media.entity.User;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.RelationshipRepository;
import com.one.social_media.repository.RelationshipTypeRepository;
import com.one.social_media.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendShipService {
    UserRepository userRepository;
    RelationshipRepository relationshipRepository;
    RelationshipTypeRepository relationshipTypeRepository;
    UserMapper userMapper;

    public List<UserResDto> getAllFriends(Long userId) {
        // 1 is friend in database
        Long relationshipTypeId = 1L;
        // get user login.
        // var context = SecurityContextHolder.getContext();
        // String name = context.getAuthentication().getName();
//        return userRepository.findAllRelationShipByUserOwner(userId);

//        return relationshipRepository.findAllByUserOwnerId(userId);


        return userRepository.findAllFriends(userId, relationshipTypeId);
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

        return new FriendshipResponseDto(userMapper.toListUserResDto(friends), userMapper.toListUserResDto(requests), userMapper.toListUserResDto(requestsSent));
    }

    @Transactional
    public void acceptFriendRequest(Long requesterId, Long receiverId) {
        RelationshipType relationshipFriend = relationshipTypeRepository.findById(1L).orElseThrow(
                () -> new AppException(HttpStatus.NOT_FOUND, "RelationshipType not found")
        );
        RelationshipKey relationshipKey = RelationshipKey
                .builder()
                .userOwnerId(requesterId)
                .userReferencedId(receiverId)
                .build();
        Relationship relationship = relationshipRepository
                .findByIdAndRelationshipTypeId(relationshipKey, 2L)
                .orElseThrow(
                        () -> new AppException(HttpStatus.NOT_FOUND, "Relationship not found")
                );
        relationship.setRelationshipType(relationshipFriend);
        relationshipRepository.save(relationship);

        RelationshipKey relationshipKeyRef = RelationshipKey
                .builder()
                .userOwnerId(receiverId)
                .userReferencedId(requesterId)
                .build();

        Relationship relationshipRef = relationshipRepository
                .findByIdAndRelationshipTypeId(relationshipKeyRef, 3L)
                .orElseThrow(
                        () -> new AppException(HttpStatus.NOT_FOUND, "Relationship not found")
                );
        relationshipRef.setRelationshipType(relationshipFriend);
        relationshipRepository.save(relationshipRef);
    }

    @Transactional
    public void removeFriendOrRequest(Long requesterId, Long receiverId) {
        RelationshipKey relationshipKey = RelationshipKey
                .builder()
                .userOwnerId(requesterId)
                .userReferencedId(receiverId)
                .build();
        relationshipRepository.deleteById(relationshipKey);
        RelationshipKey relationshipKeyRef = RelationshipKey
                .builder()
                .userOwnerId(receiverId)
                .userReferencedId(requesterId)
                .build();
        relationshipRepository.deleteById(relationshipKeyRef);
    }

}
