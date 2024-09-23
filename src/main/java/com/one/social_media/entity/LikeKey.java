package com.one.social_media.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class LikeKey implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long userId;
    private Long postId;
}
