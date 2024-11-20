package com.one.social_media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;

    @JoinColumn(name = "post_id")

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Post post;
}
