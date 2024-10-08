package com.one.social_media.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
}
