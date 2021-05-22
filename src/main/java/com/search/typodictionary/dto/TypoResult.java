package com.search.typodictionary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TypoResult {
    private List<TypoDictionaryInfo> typoDictionaryInfos;
    private List<String> words;

    @Builder
    public TypoResult(List<TypoDictionaryInfo> typoDictionaryInfos, List<String> words) {
        this.typoDictionaryInfos = typoDictionaryInfos;
        this.words = words;
    }
}
