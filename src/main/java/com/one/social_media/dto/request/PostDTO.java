package com.one.social_media.dto.request;


import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Like;
import com.one.social_media.entity.Image;

import java.util.List;

public class PostDTO {
    private Long id;
    private String content;
    private List<Image> images; // Danh sách hình ảnh
    private List<Comment> comments; // Danh sách bình luận
    private List<Like> likes; // Danh sách lượt thích
    private String gridClass; // Lớp CSS cho hiển thị lưới

    // Constructor
    public PostDTO() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getGridClass() {
        return gridClass;
    }

    public void setGridClass(String gridClass) {
        this.gridClass = gridClass;
    }
}
