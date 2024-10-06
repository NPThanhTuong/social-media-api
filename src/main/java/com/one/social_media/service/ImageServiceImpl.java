package com.one.social_media.service;

import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;

import java.util.List;

public interface ImageServiceImpl {
    List<Image> findByPost(Post post);
}
