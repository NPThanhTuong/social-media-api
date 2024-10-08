package com.one.social_media.service;

import com.one.social_media.dto.response.CommentResDto;
import com.one.social_media.dto.response.PostResDto;
import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;
import com.one.social_media.mapper.CommentMapper;
import com.one.social_media.mapper.PostMapper;
import com.one.social_media.repository.CommentRepository;
import com.one.social_media.repository.PostRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;

    PostMapper postMapper;
    CommentMapper commentMapper;


    public List<PostResDto> getAllUserPosts(Long userId) {
        var posts = postRepository.findByOwnerId(userId);
        return postMapper.toListPostResDto(posts);
    }

    public int countTotalCommentsForPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.size();
    }

    public List<PostResDto> getAllFriendPosts(Long userId) {
        // 1 is friend in database
        Long relationshipTypeId = 1L;

        List<UserResDto> userFriends = userRepository.findAllFriends(userId, relationshipTypeId);
        List<Long> userFriendIds = new ArrayList<>();

        userFriends.forEach(userResDto -> userFriendIds.add(userResDto.getId()));

        List<Post> posts = postRepository.findAllFriendPosts(userFriendIds);

        return postMapper.toListPostResDto(posts);
    }

    public List<CommentResDto> getPostComments(Long postId) {
        return commentMapper.toCommentResDto(commentRepository.findByPostIdAndParentCommentIsNull(postId));
    }
}
