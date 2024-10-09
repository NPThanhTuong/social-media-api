package com.one.social_media.service;

import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import com.one.social_media.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements PostServiceImpl{
    private final PostRepository postRepository;

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Void deletePostById(long id) {
        postRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public int totalPostOfUser(User user) {
        return  postRepository.countByUser(user);
    }

}
