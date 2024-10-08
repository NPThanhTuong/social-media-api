package com.one.social_media.mapper;

import com.one.social_media.dto.response.CommentResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Comment;
import com.one.social_media.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "parentComment.id", target = "parentCommentId")
    CommentResDto toCommentResDto(Comment comment);

    List<CommentResDto> toCommentResDto(List<Comment> comments);

    UserResDto toUserResDto(User user);
}
