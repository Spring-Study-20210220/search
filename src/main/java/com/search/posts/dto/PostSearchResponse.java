package com.search.posts.dto;

import com.search.typodictionary.dto.TypoDictionaryInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSearchResponse {

    private List<PostsInfo> data;

    private List<TypoDictionaryInfo> corrected;

    private boolean censored;

    @Builder
    public PostSearchResponse(List<PostsInfo> data, List<TypoDictionaryInfo> corrected, boolean censored) {
        this.data = data;
        this.corrected = corrected;
        this.censored = censored;
    }

    @Builder
    public PostSearchResponse(List<PostsInfo> data, List<TypoDictionaryInfo> corrected) {
        this.data = data;
        this.corrected = corrected;
        this.censored = false;
    }
}
