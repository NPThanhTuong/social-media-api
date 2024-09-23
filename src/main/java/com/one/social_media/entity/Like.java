package com.one.social_media.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_like")  // Đổi tên bảng từ 'like' thành 'post_like'
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private LikeKey id;
	
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;
    
    private Date createdAt;
}
