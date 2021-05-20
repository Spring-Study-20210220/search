package com.search.posts;

import com.search.censorword.CensorWordService;
import com.search.censorword.dto.CensoredResult;
import com.search.posts.dto.PostSearchResponse;
import com.search.posts.dto.PostsSaveResponse;
import com.search.taggeduser.TaggedUserService;
import com.search.typodictionary.TypoDictionaryService;
import com.search.typodictionary.dto.TypoDictionaryInfo;
import com.search.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TaggedUserService taggedUserService;
    private final TypoDictionaryService typoDictionaryService;
    private final UserService userService;
    private final CensorWordService censorWordService;

    @Transactional
    public PostsSaveResponse save(String content, List<Long> taggedUserIds){
        Posts posts = new Posts(content);
        Posts savedPosts =  postsRepository.save(posts);
        taggedUserService.linkedUserToPost(taggedUserIds,savedPosts);
        return new PostsSaveResponse(savedPosts.getId());
    }

    @Transactional
    public PostSearchResponse searchPosts(String keyword, Long userId) {

        List<TypoDictionaryInfo> typoWords = typoDictionaryService.checkWords(keywordSplit(keyword));
        List<String> words = typoWords.stream()
                                    .map(TypoDictionaryInfo::getTo)
                                    .collect(Collectors.toList());
        if(userService.isMinor(userId)) {
            CensoredResult censorWords = censorWordService.censorWord(words);
        }

        return null;
    }

    private String[] keywordSplit(String keyword){
        return keyword.split(" ");
    }
}
