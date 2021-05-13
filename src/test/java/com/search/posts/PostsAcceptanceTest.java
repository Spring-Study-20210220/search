package com.search.posts;

import com.search.DatabaseCleanup;
import com.search.posts.dto.PostsSaveRequest;
import com.search.posts.dto.PostsSaveResponse;
import com.search.posts.dto.PostSearchResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DatabaseCleanup dbClean;

    @AfterEach
    void tearDown() {
        dbClean.execute();
    }

    @Test
    void 포스트_등록_테스트() {
        PostsTestData postsTestData = new PostsTestData(webTestClient);
        List<Long> taggedUserIdList = new LinkedList<>();
        taggedUserIdList.add(postsTestData.userSaveForTest("테스트1", 25));
        taggedUserIdList.add(postsTestData.userSaveForTest("테스트2", 30));
        taggedUserIdList.add(postsTestData.userSaveForTest("테스트3", 35));

        PostsSaveRequest request = PostsSaveRequest.builder()
                .content("content")
                .taggedUserIds(taggedUserIdList)
                .build();

        PostsSaveResponse response = webTestClient
                .post()
                .uri("/posts")
                .body(Mono.just(request), PostsSaveRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PostsSaveResponse.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(response.getId()).isPositive();
    }

    @Test
    void 포스트에_태그된_유저가_없는_경우() {

        PostsSaveRequest request = PostsSaveRequest.builder()
                .content("content")
                .taggedUserIds(Collections.emptyList())
                .build();

        PostsSaveResponse response = webTestClient
                .post()
                .uri("/posts")
                .body(Mono.just(request), PostsSaveRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PostsSaveResponse.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(response.getId()).isPositive();
    }

    @Test
    void 포스트를_키워드로_검색() {
        PostsTestData postsTestData = new PostsTestData(webTestClient);
        Long user_id = postsTestData.userSaveForTest("testuser", 18);

        String keywordParam = "토비의 스프링";
        String userIdParam = user_id.toString();

        PostSearchResponse response = webTestClient
                .get()
                .uri("/posts/list?keyword=" + keywordParam + "?user-id=" + userIdParam)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PostSearchResponse.class)
                .returnResult()
                .getResponseBody();
    }
}
