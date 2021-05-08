package com.search.user;

import com.search.user.dto.UserRequest;
import com.search.user.dto.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 유저_등록_테스트() {
        UserRequest request = new UserRequest("peach", 25);

        UserResponse response = webTestClient
                .post()
                .body(Mono.just(request), UserRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponse.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(response.getId()).isPositive();
    }
}
