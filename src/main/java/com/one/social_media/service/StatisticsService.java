package com.one.social_media.service;

import com.one.social_media.repository.CommentRepository;
import com.one.social_media.repository.LikeRepository;
import com.one.social_media.repository.PostRepository;
import com.one.social_media.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Map<Integer, Long> getLikesCountByMonth(int year) {
        Map<Integer, Long> likesCount = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            likesCount.put(i, 0L);
        }

        List<Object[]> results = likeRepository.countLikesByMonth(year);
        for (Object[] result : results) {
            int month = (Integer) result[0];
            long count = ((Number) result[1]).longValue();
            likesCount.put(month, count);
        }

        return likesCount;
    }

    public Map<Integer, Long> getCommentsCountByMonth(int year) {
        Map<Integer, Long> commentsCount = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            commentsCount.put(i, 0L);
        }

        List<Object[]> results = commentRepository.countCommentsByMonth(year);
        for (Object[] result : results) {
            int month = (Integer) result[0];
            long count = ((Number) result[1]).longValue();
            commentsCount.put(month, count);
        }

        return commentsCount;
    }

    public Map<Integer, Long> getNewUsersCountByMonth(int year) {
        Map<Integer, Long> newUsersCount = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            newUsersCount.put(i, 0L);
        }

        List<Object[]> results = userRepository.countNewUsersByMonth(year);
        for (Object[] result : results) {
            int month = (Integer) result[0];
            long count = ((Number) result[1]).longValue();
            newUsersCount.put(month, count);
        }

        return newUsersCount;
    }

    public Map<Integer, Long> getPostsCountByMonth(int year) {
        Map<Integer, Long> postsCount = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            postsCount.put(i, 0L);
        }

        List<Object[]> results = postRepository.countPostsByMonth(year);
        for (Object[] result : results) {
            int month = (Integer) result[0];
            long count = ((Number) result[1]).longValue();
            postsCount.put(month, count);
        }
        return postsCount;
    }
}
