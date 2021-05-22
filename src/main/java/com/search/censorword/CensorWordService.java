package com.search.censorword;

import com.search.censorword.dto.CensoredResult;
import com.search.posts.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CensorWordService {
    private final CensorWordRepository censorWordRepository;

    @Transactional
    public CensoredResult censorPostsList(List<Posts> postsList) {
        List<Posts> result = postsList.stream()
                .filter(it -> !censorText(it.getContent()))
                .collect(Collectors.toList());
        boolean isCensored = postsList.size() != result.size();
        return CensoredResult.builder()
                .censoredPosts(result)
                .censored(isCensored)
                .build();
    }

    @Transactional
    public boolean censorText(String text) {
        List<CensorWord> censorWordList = censorWordRepository.findAll();
        return censorWordList.stream()
                .map(CensorWord::getWord)
                .anyMatch(text::contains);
    }
}
