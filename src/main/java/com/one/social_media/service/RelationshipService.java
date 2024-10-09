package com.one.social_media.service;

import com.one.social_media.entity.User;
import com.one.social_media.repository.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;

    public int totalFriendsOfUser(User user) {
        return relationshipRepository.countFriendsByUser(user);
    }
}