package com.search.post.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CorrectionResponse {
    private final String from;
    private final String to;
}
