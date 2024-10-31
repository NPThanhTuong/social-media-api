package com.one.social_media.config;

import com.one.social_media.entity.User;
import com.one.social_media.repository.RoleRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                var role = roleRepository.findByName("ADMIN").orElse(null);

                var user = User.builder()
                        .email("admin@gmail.com")
                        .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995552/no-avatar_nj3kwi.png")
                        .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995553/no-image_ow1ois.jpg")
                        .name("Administrator")
                        .phone("0928746784")
                        .role(role)
                        .password(passwordEncoder.encode("admin12345"))
                        .build();

                userRepository.save(user);
            }
        };
    }
}
