package com.one.social_media.mapper;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserResDto toUserResDto(User user);

    List<UserResDto> toListUserResDto(List<User> user);

}
