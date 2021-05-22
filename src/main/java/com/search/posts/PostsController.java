package com.search.posts;

import com.search.posts.dto.PostSearchResponse;
import com.search.posts.dto.PostsSaveRequest;
import com.search.posts.dto.PostsSaveResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("")
    public ResponseEntity<PostsSaveResponse> save(@RequestBody PostsSaveRequest requestDto,
                                                  @RequestHeader(value = "Authorization") long userId) {
        PostsSaveResponse responseDto = postsService.save(userId, requestDto.getContent(), requestDto.getTaggedUserIds());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @Validated
    @GetMapping("/list")
    public ResponseEntity<PostSearchResponse> searchPosts(
            @RequestParam(name = "keyword") @Length(min = 2) String keyword,
            @RequestParam(name = "user-id", required = false) Long userId
    ) {

        PostSearchResponse responseDto = postsService.searchPosts(keyword, userId);

        return ResponseEntity.ok(responseDto);
    }
}
