package com.search.post.service;

import com.search.post.entity.Post;
import com.search.post.repository.PostRepository;
import com.search.post.req.SavePostRequest;
import com.search.post.res.SavePostResponse;
import com.search.user.entity.User;
import com.search.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private PostService postService;

    private Post post;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "test", 19);
        post = new Post(1L, user, "test contents", 0, LocalDateTime.now(), LocalDateTime.now());
    }

    //todo: argument captor
    @Test
    void create() {
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(postRepository.save(any())).willReturn(post);

        SavePostRequest savePostRequest = new SavePostRequest(1L, "test contents", new ArrayList<>());
        SavePostResponse savePostResponse = postService.save(savePostRequest);

        assertThat(savePostResponse.getContent()).isEqualTo(savePostRequest.getContent());
        assertThat(savePostResponse.getUserResponse().getName()).isEqualTo(user.getName());
    }
}
