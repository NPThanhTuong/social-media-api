package com.one.social_media.service;

import com.one.social_media.dto.request.LikePostReqDto;
import com.one.social_media.dto.request.PostReqDto;
import com.one.social_media.dto.response.LikePostResDto;
import com.one.social_media.dto.response.PostResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.*;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.CommentMapper;
import com.one.social_media.mapper.PostMapper;
import com.one.social_media.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostService {
    PostRepository postRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;
    ImageRepository imageRepository;
    LikeRepository likeRepository;
    ReactionRepository reactionRepository;

    PostMapper postMapper;
    CommentMapper commentMapper;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PostResDto> getAllUserPosts() {
        var userId = getLoginUserId();
        var posts = postRepository.findByOwnerId(userId);
        return postMapper.toListPostResDto(posts);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PostResDto> getAllUserPosts(Long userId) {
        var posts = postRepository.findByOwnerId(userId);
        return postMapper.toListPostResDto(posts);
    }

    public int countTotalCommentsForPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostIdAndDeletedAtIsNull(postId);
        return comments.size();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PostResDto> getAllFriendPosts() {
        Long relationshipTypeId = 1L;

        var userId = getLoginUserId();
        List<UserResDto> userFriends = userRepository.findAllFriends(userId, relationshipTypeId);
        List<Long> userFriendIds = new ArrayList<>();

        userFriends.forEach(userResDto -> userFriendIds.add(userResDto.getId()));

        List<Post> posts = postRepository.findAllFriendPosts(userFriendIds);

        return postMapper.toListPostResDto(posts);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

    @Transactional
    public Post createNewPost(PostReqDto postReqDto) {
        var userId = getLoginUserId();
        Post newPost = postMapper.toPost(postReqDto);
        newPost.setOwner(userRepository.getReferenceById(userId));
        Post savedPost = postRepository.save(newPost);


        if (!postReqDto.getImages().isEmpty()) {
            List<Image> images = postMapper.toListImage(postReqDto.getImages());
            images.forEach(image -> {
                image.setPost(postRepository.getReferenceById(savedPost.getId()));
            });
            imageRepository.saveAll(images);

        }
        return savedPost;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

    public LikePostResDto likePost(LikePostReqDto likePostReqDto) {
        var userId = getLoginUserId();

        LikeKey likeKey = new LikeKey(userId, likePostReqDto.getPostId());

        Like likePost = likeRepository.findById(likeKey).orElse(null);

        if (likePost == null) {
            Like newLikePost = Like.builder()
                    .id(new LikeKey(userId, likePostReqDto.getPostId()))
                    .post(postRepository.getReferenceById(likePostReqDto.getPostId()))
                    .user(userRepository.getReferenceById(userId))
                    .reaction(reactionRepository.getReferenceById(likePostReqDto.getReactionId()))
                    .build();

            likeRepository.save(newLikePost);

            return LikePostResDto.builder()
                    .message("Like post successfully")
                    .success(true)
                    .build();
        } else {
            likeRepository.delete(likePost);

            return LikePostResDto.builder()
                    .message("Unlike post successfully")
                    .success(true)
                    .build();
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PostResDto> getLikedPost() {
        var userId = getLoginUserId();
        List<Post> posts = likeRepository.findAllPostByUserId(userId);
        return postMapper.toListPostResDto(posts);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Long> getListLikePostIds() {
        var userId = getLoginUserId();

        var posts = likeRepository.findAllPostByUserId(userId);

        List<Long> listId = new ArrayList<>();
        posts.forEach(post -> listId.add(post.getId()));
        return listId;
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Void deletePostById(long id) {
        postRepository.deleteById(id);
        return null;
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public int totalPostOfUser(User user) {
        return postRepository.countByUser(user);
    }

    public Page<Post> searchPostByDate(Date fromDate, Date toDate, Pageable pageable) {
        if (fromDate != null && toDate != null) {
            return postRepository.filterByDate(fromDate, toDate, pageable);
        }
        return Page.empty(pageable);
    }

    public Page<Post> findAllSortedByDateDesc(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Post> findAllSortedByDateAsc(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtAsc(pageable);
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private long getLoginUserId() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user.getId();
    }
}
