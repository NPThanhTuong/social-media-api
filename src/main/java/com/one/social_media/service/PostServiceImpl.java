package com.one.social_media.service;

import com.one.social_media.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostServiceImpl {
    Page<Post> getAllPosts(Pageable pageable); // Lấy danh sách bài đăng với phân trang
    Void deletePostById(long id);
    Optional<Post> findById(Long id);
}
