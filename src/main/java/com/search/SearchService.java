package com.search;

import com.search.censor.service.CensoredWordService;
import com.search.dictionary.CorrectedSentence;
import com.search.dictionary.service.DictionaryService;
import com.search.post.entity.Post;
import com.search.post.res.PostResponse;
import com.search.post.res.SearchPostsResponse;
import com.search.post.service.PostService;
import com.search.sort.service.SortCriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchService {
    private final PostService postService;
    private final CensoredWordService censoredWordService;
    private final DictionaryService dictionaryService;
    private final SortCriteriaService sortCriteriaService;

    public SearchPostsResponse search(String keyword, int age) {
        CorrectedSentence correctedSentence = dictionaryService.correct(keyword);
        List<Post> filteredPosts = postService.search(correctedSentence.getValue());

        if (age >= 19) {
            List<PostResponse> postResponses = filteredPosts.stream()
                    .map(post -> new PostResponse(post.getUser().getId(), post.getUser().getName(), post.getContent()))
                    .collect(Collectors.toList());

            return new SearchPostsResponse(postResponses, correctedSentence.getCorrections(), false);
        }

        List<Post> censoredPosts = censoredWordService.censor(filteredPosts);

        boolean isCensored = censoredPosts.size() == filteredPosts.size();
        List<PostResponse> postResponses = censoredPosts.stream()
                .map(post -> new PostResponse(post.getUser().getId(), post.getUser().getName(), post.getContent()))
                .collect(Collectors.toList());

        return new SearchPostsResponse(postResponses, correctedSentence.getCorrections(), isCensored);
    }
}
