package com.search.post.res;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long userId;
    private String userName;
    private String content;
}
