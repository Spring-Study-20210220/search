package com.search.censorword.dto;

import com.search.posts.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CensoredResult {
    private boolean censored;
    private List<Posts> censoredPosts;

    @Builder
    public CensoredResult(boolean censored, List<Posts> censoredPosts) {
        this.censored = censored;
        this.censoredPosts = censoredPosts;
    }
}
