package com.search.post.controller;

import com.search.SearchService;
import com.search.post.req.SavePostRequest;
import com.search.post.res.SavePostResponse;
import com.search.post.res.SearchPostsResponse;
import com.search.post.service.PostService;
import com.search.user.res.UserResponse;
import com.search.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/post")
@RestController
public class PostController {
    private final SearchService searchService;
    private final PostService postService;
    private final UserService userService;

    public PostController(SearchService searchService, PostService postService, UserService userService) {
        this.searchService = searchService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public SavePostResponse savePost(SavePostRequest req) {
        return postService.save(req);
    }

    @GetMapping("/list")
    public SearchPostsResponse searchPost(@RequestParam String keyword, @RequestParam Long userId) {
        UserResponse userResponse = userService.get(userId);
        return searchService.search(keyword, userResponse.getAge());
    }
}
