package com.search.posts;

import com.search.user.dto.UserRequest;
import com.search.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsTestData {

    private final WebTestClient webTestClient;

    public PostsTestData(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    public Long userSaveForTest(String name, int age){
        UserRequest request = new UserRequest(name, age);

        UserResponse userResponse = webTestClient
                .post()
                .uri("/user")
                .body(Mono.just(request), UserRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponse.class)
                .returnResult()
                .getResponseBody();
        return userResponse.getId();
    }
}
