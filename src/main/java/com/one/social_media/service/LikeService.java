package com.one.social_media.service;

import com.one.social_media.entity.Like;
import com.one.social_media.entity.Post;
import com.one.social_media.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeServiceImpl{
    private final LikeRepository likeRepository;

    @Override
    public Optional<List<Like>> findByPostId(Long id) {
        return likeRepository.findByPostId(id);
    }
}
