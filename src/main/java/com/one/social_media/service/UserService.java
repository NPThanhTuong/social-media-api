package com.one.social_media.service;

import com.one.social_media.entity.User;
import com.one.social_media.repository.RoleRepository;
import com.one.social_media.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
