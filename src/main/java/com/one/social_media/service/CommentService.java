package com.one.social_media.service;

import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;
import com.one.social_media.repository.CommentRepository;
import com.one.social_media.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
