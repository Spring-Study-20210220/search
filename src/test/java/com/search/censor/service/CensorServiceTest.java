package com.search.censor.service;

import com.search.censor.entity.CensoredWord;
import com.search.censor.repository.CensoredWordRepository;
import com.search.post.entity.Post;
import com.search.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CensorServiceTest {

    @Mock
    private CensoredWordRepository censoredWordRepository;

    @InjectMocks
    private CensoredWordService censoredWordService;

    private User user = new User(0L, "jon", 18);

    @Test
    void 검열하고_남은_Posts() {
        given(censoredWordRepository.findFirstByOrderByIdDesc()).willReturn(new CensoredWord(0L, "fuck, shit"));

        List<Post> posts = List.of(
                new Post(1L, user, "fuck contents", 0, LocalDateTime.now(), LocalDateTime.now()),
                new Post(2L, user, "shit contents", 0, LocalDateTime.now(), LocalDateTime.now()),
                new Post(3L, user, "test contents", 0, LocalDateTime.now(), LocalDateTime.now())
        );
        List<Post> censoredPosts = censoredWordService.censor(posts);

        assertThat(censoredPosts.size()).isEqualTo(1);
        assertThat(censoredPosts.get(0).getId()).isEqualTo(3L);
    }
}
