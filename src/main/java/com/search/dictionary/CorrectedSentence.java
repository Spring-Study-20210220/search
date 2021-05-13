package com.search.dictionary;

import com.search.post.res.CorrectionResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CorrectedSentence {
    private final String value;
    private final List<CorrectionResponse> corrections;
}
