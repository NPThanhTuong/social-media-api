package com.one.social_media.service;

import com.one.social_media.entity.Post;

import java.util.List;

public interface PostServiceImpl {
    List<Post> getAllPosts();
    Void deletePostById(long id);
}
