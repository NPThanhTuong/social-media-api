package com.one.social_media.service;

import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;

import java.util.List;

public interface CommentServiceImpl {
    List<Comment> findByPost(Post post);
}
