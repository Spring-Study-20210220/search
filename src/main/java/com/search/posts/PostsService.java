package com.search.posts;

import com.search.censoredword.CensoredWordService;
import com.search.posts.dto.PostSearchResponse;
import com.search.posts.dto.PostsSaveResponse;
import com.search.taggeduser.TaggedUserRepository;
import com.search.taggeduser.TaggedUserService;
import com.search.typodictionary.TypoDictionaryService;
import com.search.user.UserRepository;
import com.search.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TaggedUserService taggedUserService;
    private final TypoDictionaryService typoDictionaryService;
    private final UserService userService;
    private final CensoredWordService censoredWordService;

    @Transactional
    public PostsSaveResponse save(String content, List<Long> taggedUserIds){
        Posts posts = new Posts(content);
        Posts savedPosts =  postsRepository.save(posts);
        taggedUserService.linkedUserToPost(taggedUserIds,savedPosts);
        return new PostsSaveResponse(savedPosts.getId());
    }

    @Transactional
    public PostSearchResponse searchPosts(String keyword, Long userId) {

        List<String> words = typoDictionaryService.checkWords(keywordSplit(keyword));
        int age = userService.getUserAge(userId);
        if(age < 19) {
            words = censoredWordService.censordWord(words);
        }

        return null;
    }

    private String[] keywordSplit(String keyword){
        return keyword.split(" ");
    }
}
