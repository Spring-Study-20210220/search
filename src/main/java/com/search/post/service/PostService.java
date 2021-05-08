package com.search.post.service;

import com.search.post.entity.Post;
import com.search.post.repository.PostRepository;
import com.search.post.req.SavePostRequest;
import com.search.post.res.SavePostResponse;
import com.search.user.entity.User;
import com.search.user.repository.UserRepository;
import com.search.user.res.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public SavePostResponse save(SavePostRequest savePostRequest) {
        User user = userRepository.findById(savePostRequest.getUserId()).orElseThrow(IllegalArgumentException::new);
        LocalDateTime now = LocalDateTime.now();

        Post savedPost = postRepository.save(new Post(0L, user, savePostRequest.getContent(), 0, now, now));

        return new SavePostResponse(
                savedPost.getId(),
                savedPost.getContent(),
                UserResponse.of(savedPost.getUser())
        );
    }
}
