package com.one.social_media.service;

import com.one.social_media.entity.Like;
import com.one.social_media.repository.LikeRepository;
import com.one.social_media.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    public Optional<List<Like>> findByPostId(Long id) {
        return likeRepository.findByPostId(id);
    }


}
