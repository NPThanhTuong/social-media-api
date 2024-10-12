package com.one.social_media.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long postId;
}
