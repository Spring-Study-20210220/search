package com.search.post.res;

import com.search.user.res.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SavePostResponse {
    private final Long postId;
    private final String content;
    private final UserResponse userResponse;
}
