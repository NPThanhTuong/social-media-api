package com.one.social_media.mapper;

import com.one.social_media.dto.request.ImageReqDto;
import com.one.social_media.dto.request.PostReqDto;
import com.one.social_media.dto.response.ImageResDto;
import com.one.social_media.dto.response.PostResDto;
import com.one.social_media.dto.response.PostResWithLikeDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface PostMapper {
    @Mapping(target = "totalLike", ignore = true)
    @Mapping(target = "totalComment", ignore = true)
    PostResDto toPostResDto(Post post);

    List<PostResDto> toListPostResDto(List<Post> posts);

    ImageResDto toImageResDto(Image image);

    List<ImageResDto> toListImageResDto(List<Image> images);

    UserResDto toUserResDto(User user);

    PostResWithLikeDto toPostResWithLikeDto(Post post);

    // ----Reverse----

    @Mapping(target = "images", ignore = true)
    Post toPost(PostReqDto postReqDto);

    Image toImage(ImageReqDto imageReqDto);

    List<Image> toListImage(List<ImageReqDto> imageReqDtoList);

    @AfterMapping
    default void calculateTotalLike(@MappingTarget PostResDto postResDtoDto, Post post) {
        int totalLike = post.getLikes().size();
        int totalComment = post.getComments()
                .stream()
                .filter(comment -> comment.getDeletedAt() == null)
                .toList().size();

        postResDtoDto.setTotalLike(totalLike);
        postResDtoDto.setTotalComment(totalComment);
    }
}
