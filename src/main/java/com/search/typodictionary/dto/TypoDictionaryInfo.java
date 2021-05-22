package com.search.typodictionary.dto;

import com.search.typodictionary.TypoDictionary;
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

    public static TypoDictionaryInfo from(TypoDictionary typoDictionary) {
        return TypoDictionaryInfo.builder()
                .from(typoDictionary.getTypo())
                .to(typoDictionary.getWord())
                .build();
    }
}
