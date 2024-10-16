package com.one.social_media.dto.response;

import com.one.social_media.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipResponseDto {
    private List<UserResDto> friends;          // Danh sách bạn bè
    private List<UserResDto> requests;         // Danh sách yêu cầu kết bạn
    private List<UserResDto> requestsSent;     // Danh sách yêu cầu đã gửi
}
