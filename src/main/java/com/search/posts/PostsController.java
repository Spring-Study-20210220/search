package com.search.posts;

import com.search.posts.dto.PostsSaveRequest;
import com.search.posts.dto.PostsSaveResponse;
import com.search.posts.dto.PostSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("")
    public ResponseEntity<PostsSaveResponse> save(@RequestBody PostsSaveRequest requestDto){
        PostsSaveResponse responseDto = postsService.save(requestDto.getContent(), requestDto.getTaggedUserIds());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<PostSearchResponse> searchPosts(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "user-id") Long userId
    ) {

        PostSearchResponse responseDto = postsService.searchPosts(keyword, userId);

        return ResponseEntity.ok(responseDto);
    }
}
