package com.search.taggeduser;

import com.search.posts.Posts;
import com.search.user.User;
import com.search.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TaggedUserServiceTest {

    @InjectMocks
    private TaggedUserService taggedUserService;
    @Mock
    private TaggedUserRepository taggedUserRepository;
    @Mock
    private UserRepository userRepository;

    //List<Long> taggedIds, Posts posts
    @Test
    void 포스트에_유저를_태깅() {
        //given
        User user1 = User.builder()
                .name("testuser1")
                .age(18)
                .build();
        User user2 = User.builder()
                .name("testuser2")
                .age(20)
                .build();
        given(userRepository.findById(any()))
                .willReturn(Optional.of(user1))
                .willReturn(Optional.of(user2));
        Posts posts = Posts.builder()
                .content("conteentblakblak")
                .build();
        TaggedUser taggedUser1 = TaggedUser.builder()
                .posts(posts)
                .user(user1)
                .build();
        TaggedUser taggedUser2 = TaggedUser.builder()
                .posts(posts)
                .user(user2)
                .build();

        given(taggedUserRepository.save(any()))
                .willReturn(taggedUser1)
                .willReturn(taggedUser2);

        //when
        taggedUserService.linkedUserToPost(Arrays.asList(1L, 2L), posts);

        //then
        assertThat(user1.getTaggedUsers().size()).isEqualTo(1);
        assertThat(user2.getTaggedUsers().size()).isEqualTo(1);
        assertThat(posts.getTaggedUsers().size()).isEqualTo(2);
    }

    @Test
    void 일치하지않는_유저_아이디가_들어왔을_경우() {
        //given
        Posts posts = Posts.builder()
                .content("conteentblakblak")
                .build();
        given(userRepository.findById(any()))
                .willThrow(new IllegalArgumentException("일치하지 않는 ID입니다"));

        //when

        //then
        Assertions.assertThatThrownBy(
                () -> taggedUserService.linkedUserToPost(Collections.singletonList(1L), posts))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
