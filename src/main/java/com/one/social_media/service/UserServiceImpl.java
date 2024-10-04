package com.one.social_media.service;

import com.one.social_media.entity.User;

import java.util.Optional;

public interface UserServiceImpl {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

}
