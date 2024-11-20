package com.one.social_media.service;

import com.one.social_media.dto.response.FriendshipResponseDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Relationship;
import com.one.social_media.entity.RelationshipKey;
import com.one.social_media.entity.RelationshipType;
import com.one.social_media.entity.User;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.RelationshipRepository;
import com.one.social_media.repository.RelationshipTypeRepository;
import com.one.social_media.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
    AuthService authService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserResDto> getAllFriends() {
        var userId = getLoginUserId();
        Long relationshipTypeId = 1L;

        return userRepository.findAllFriends(userId, relationshipTypeId);
    }

    public FriendshipResponseDto getFriendshipDetails() {
        Long userId = getLoginUserId();
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
    public void acceptFriendRequest(Long receiverId) {
        Long requesterId = getLoginUserId();
        RelationshipType relationshipFriend = relationshipTypeRepository.findById(1L).orElseThrow(
                () -> new AppException(ErrorCode.RELATIONSHIP_TYPE_NOT_EXIST)
        );
        RelationshipKey relationshipKey = RelationshipKey
                .builder()
                .userOwnerId(requesterId)
                .userReferencedId(receiverId)
                .build();
        Relationship relationship = relationshipRepository
                .findByIdAndRelationshipTypeId(relationshipKey, 2L)
                .orElseThrow(
                        () -> new AppException(ErrorCode.RELATIONSHIP_NOT_EXIST)
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
                        () -> new AppException(ErrorCode.RELATIONSHIP_NOT_EXIST)
                );
        relationshipRef.setRelationshipType(relationshipFriend);
        relationshipRepository.save(relationshipRef);
    }

    @Transactional
    public void removeFriendOrRequest(Long receiverId) {
        Long requesterId = getLoginUserId();
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserResDto> getSuggestedFriends() {
        Long userId = getLoginUserId();
        return userRepository.findSuggestedFriends(userId);
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }

    @Transactional
    public void sendFriendRequest(Long receiverId) {
        Long senderId = getLoginUserId();

        if (senderId.equals(receiverId)) {
            throw new AppException(ErrorCode.INVALID_REQUEST);
        }

        // Kiểm tra nếu đã tồn tại bất kỳ mối quan hệ nào giữa sender và receiver
        boolean alreadyRelated = relationshipRepository
                .findById(RelationshipKey.builder()
                        .userOwnerId(senderId)
                        .userReferencedId(receiverId)
                        .build())
                .isPresent() ||
                relationshipRepository
                        .findById(RelationshipKey.builder()
                                .userOwnerId(receiverId)
                                .userReferencedId(senderId)
                                .build())
                        .isPresent();

        if (alreadyRelated) {
            throw new AppException(ErrorCode.RELATIONSHIP_ALREADY_EXISTS);
        }

        // Loại mối quan hệ "gửi yêu cầu"
        RelationshipType requestType = relationshipTypeRepository.findById(3L).orElseThrow(
                () -> new AppException(ErrorCode.RELATIONSHIP_TYPE_NOT_EXIST)
        );

        // Loại mối quan hệ "nhận yêu cầu"
        RelationshipType requestReceivedType = relationshipTypeRepository.findById(2L).orElseThrow(
                () -> new AppException(ErrorCode.RELATIONSHIP_TYPE_NOT_EXIST)
        );

        // Tạo mối quan hệ "gửi yêu cầu" từ sender -> receiver
        Relationship sendRequest = Relationship.builder()
                .id(RelationshipKey.builder()
                        .userOwnerId(senderId) // Người gửi là userOwner
                        .userReferencedId(receiverId) // Người nhận là userReferenced
                        .build())
                .userOwner(userRepository.findById(senderId).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED)
                ))
                .userReferenced(userRepository.findById(receiverId).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED)
                ))
                .updatedAt(LocalDateTime.now())
                .relationshipType(requestType)
                .build();
        relationshipRepository.save(sendRequest);

        // Tạo mối quan hệ "nhận yêu cầu" từ receiver -> sender
        Relationship receiveRequest = Relationship.builder()
                .id(RelationshipKey.builder()
                        .userOwnerId(receiverId) // Người nhận là userOwner
                        .userReferencedId(senderId) // Người gửi là userReferenced
                        .build())
                .userOwner(userRepository.findById(receiverId).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED)
                ))
                .userReferenced(userRepository.findById(senderId).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED)
                ))
                .updatedAt(LocalDateTime.now())
                .relationshipType(requestReceivedType)
                .build();
        relationshipRepository.save(receiveRequest);
    }



}
