package com.search.posts.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsSaveRequest {
    private String content;
    private List<Long> taggedUserIds;

    @Builder
    public PostsSaveRequest(String content, List<Long> taggedUserIds) {
        this.content = content;
        this.taggedUserIds = taggedUserIds;
    }

}
