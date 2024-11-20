package com.one.social_media.mapper;

import com.one.social_media.dto.request.RegisterReqDto;
import com.one.social_media.dto.request.UpdateUserProfileDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface UserMapper {
    UserResDto toUserResDto(User user);

    List<UserResDto> toListUserResDto(List<User> user);

    User toUser(RegisterReqDto registerReqDto);

    void updateToUser(UpdateUserProfileDto updateUserProfileDto, @MappingTarget User user);
}
