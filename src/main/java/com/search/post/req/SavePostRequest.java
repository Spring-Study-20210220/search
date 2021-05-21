package com.search.post.req;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SavePostRequest {
    private final Long userId;
    private final String content;
}
