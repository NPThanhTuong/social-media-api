package com.one.social_media.service;

import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;
import com.one.social_media.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<Image> findByPost(Post post) {
        return imageRepository.findByPost(post);
    }
}
