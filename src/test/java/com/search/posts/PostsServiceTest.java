package com.search.posts;

import com.search.censoredword.CensoredWordService;
import com.search.posts.dto.PostSearchResponse;
import com.search.taggeduser.TaggedUserService;
import com.search.typodictionary.TypoDictionaryService;
import com.search.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private PostsRepository postsRepository;
    @Mock
    private TypoDictionaryService typoDictionaryService;
    @Mock
    private UserService userService;
    @Mock
    private CensoredWordService censoredWordService;
    @InjectMocks
    private PostsService postsService;

    @Test
    void searchPostsTest() {
        //given
        given(typoDictionaryService.checkWords(any())).willReturn();
        given(censoredWordService.censordWord(any())).willReturn(Arrays.asList());
        given(userService.minorCheck(any())).willReturn(true);

        //when
        PostSearchResponse postSearchResponse = postsService.searchPosts("스포링 책 팝니다",1L);

        //then
        assertThat(postSearchResponse.getCensored()).isEqualTo(true);

    }
}
