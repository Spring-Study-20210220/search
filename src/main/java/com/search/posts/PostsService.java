package com.search.posts;

import com.search.censorword.CensorWordService;
import com.search.censorword.dto.CensoredResult;
import com.search.posts.dto.PostSearchResponse;
import com.search.posts.dto.PostsInfo;
import com.search.posts.dto.PostsSaveResponse;
import com.search.taggeduser.TaggedUserService;
import com.search.typodictionary.TypoDictionaryService;
import com.search.typodictionary.dto.TypoDictionaryInfo;
import com.search.user.UserService;
import com.search.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

        List<String> toWords = typoWords.stream()
                                    .map(TypoDictionaryInfo::getTo)
                                    .collect(Collectors.toList());
        List<String> censoredWords = Collections.emptyList();
        boolean censored = false;
        if(userService.isMinor(userId)) {
            CensoredResult censorWords = censorWordService.censorWord(toWords);
            censored = censorWords.isCensored();
            censoredWords = censorWords.getCensoredWords();
        }

        List<String> finalCensoredWords = censoredWords;
        List<Posts> posts = postsRepository.findAll().stream()
                .filter(it -> it.containsWords(toWords))
                .filter(it -> it.notContainsCensoredWords(finalCensoredWords))
                .collect(Collectors.toList());

        List<PostsInfo> data = posts.stream()
                .map(it -> new PostsInfo(
                        it.getId(),
                        it.getUser().getName(),
                        it.getContent(),
                        it.getTaggedUsers().stream()
                                .map(taggedUser -> new UserInfo(
                                        taggedUser.getUser().getId(),
                                        taggedUser.getUser().getName()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        updatePostsViewCount(posts);

        return PostSearchResponse.builder()
                .censored(censored)
                .corrected(typoWords)
                .data(data)
                .build();
    }

    private void updatePostsViewCount(List<Posts> posts) {
        for(Posts post : posts) {
            int count = post.getViewcnt();
            post.updateViewcnt(count+1);
            postsRepository.save(post);
        }
    }

    private String[] keywordSplit(String keyword){
        return keyword.split(" ");
    }
}
