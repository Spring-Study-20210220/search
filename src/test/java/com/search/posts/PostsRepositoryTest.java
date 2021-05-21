package com.search.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(PostsRepositorySupport.class)
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsRepositorySupport postsRepositorySupport;

    @Test
    void 검색쿼리테스트(){

        Posts posts1 = Posts.builder()
                .content("토비의 스프링 책 구매하실분 찾아요")
                .build();
        Posts posts2 = Posts.builder()
                .content("토비의 파이썬 책 구매하실분 찾아요")
                .build();
        Posts posts3 = Posts.builder()
                .content("뚜비의 스프 책 구비하실분 찾아요")
                .build();
        Posts posts4 = Posts.builder()
                .content("저는 바보가 아니라서 스프링을 공부할 수 있습니다")
                .build();

        postsRepository.save(posts1);
        postsRepository.save(posts2);
        postsRepository.save(posts3);
        postsRepository.save(posts4);

        List<String> keyword = Arrays.asList("스프링","토비","구매");
        List<String> censored = Arrays.asList("바보", "멍청이", "병신");

        List<Posts> Result = postsRepositorySupport.findByKeywords(keyword, censored);
        assertThat(Result.size()).isEqualTo(2);
    }
}
