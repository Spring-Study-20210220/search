package com.search.censorword.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CensoredResult {
    private boolean censored;
    private List<String> censoredWords;

    @Builder
    public CensoredResult(boolean censored, List<String> censoredWords) {
        this.censored = censored;
        this.censoredWords = censoredWords;
    }
}
