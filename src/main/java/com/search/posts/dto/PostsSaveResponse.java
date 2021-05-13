package com.search.posts.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsSaveResponse {
    private Long id;

    @Builder
    public PostsSaveResponse(Long id) {
        this.id = id;
    }
}
