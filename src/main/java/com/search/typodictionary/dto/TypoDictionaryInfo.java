package com.search.typodictionary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TypoDictionaryInfo {
    private String from;
    private String to;

    @Builder
    public TypoDictionaryInfo(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
