package com.one.social_media.service;

import com.one.social_media.dto.request.ChatUserReqDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import com.one.social_media.enums.UserStatus;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.UserMapper;
import com.one.social_media.repository.ImageRepository;
import com.one.social_media.repository.RoleRepository;
import com.one.social_media.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.one.social_media.dto.request.UpdateUserProfileDto; // Import DTO

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    ImageRepository imageRepository;  // Sử dụng ImageRepository


    UserMapper userMapper;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserResDto getUserInfo() {
        var userId = getLoginUserId();

        var user = findUserById(userId);
        return userMapper.toUserResDto(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }

    // chat service
    public User connectUser(ChatUserReqDto chatUserReqDto) {
        var user = findByEmail(chatUserReqDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setStatus(UserStatus.ONLINE);

        return userRepository.save(user);
    }

    public User disconnectUser(ChatUserReqDto chatUserReqDto) {
        var storedUser = userRepository.findByEmail(chatUserReqDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        storedUser.setStatus(UserStatus.OFFLINE);
        return userRepository.save(storedUser);
    }

    public List<UserResDto> findConnectedUsers() {
        return userMapper.toListUserResDto(userRepository.findAllByStatus(UserStatus.ONLINE));
    }
    // Phương thức cập nhật hồ sơ
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserResDto updateUserProfile(UpdateUserProfileDto userProfileDto) {
        User user = userRepository.findById(this.getLoginUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateToUser(userProfileDto,user);
        return userMapper.toUserResDto( userRepository.save(user));
    }

    // Phương thức lấy danh sách hình ảnh của người dùng
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Transactional   // Đảm bảo phiên vẫn mở khi truy xuất dữ liệu
    public List<Image> getUserImages() {
        Long userId = getLoginUserId();
        return imageRepository.findAllImagesByUserId(userId);  // Gọi ImageRepository để lấy danh sách hình ảnh
    }
}