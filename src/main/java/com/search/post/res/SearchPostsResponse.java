package com.search.post.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SearchPostsResponse {
    private final List<PostResponse> posts;
    private final List<CorrectionResponse> corrections;
    private final Boolean Censored;
}
