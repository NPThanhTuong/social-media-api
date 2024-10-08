package com.one.social_media.service;

import com.one.social_media.entity.Like;
import com.one.social_media.entity.Post;

import java.util.List;
import java.util.Optional;

public interface LikeServiceImpl {
    Optional<List<Like>> findByPostId(Long id);
}
