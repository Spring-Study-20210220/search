package com.search.posts;

import com.search.posts.dto.PostsSaveRequest;
import com.search.posts.dto.PostsSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    @PostMapping("/posts")
    public ResponseEntity<PostsSaveResponse> save(@RequestBody PostsSaveRequest requestDto){
        PostsSaveResponse responseDto = postsService.save(requestDto.getContent(), requestDto.getTaggedUserIds());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }
}
