package com.search.posts;

import com.search.censorword.CensorWordService;
import com.search.censorword.dto.CensoredResult;
import com.search.posts.dto.PostSearchResponse;
import com.search.posts.dto.PostsInfo;
import com.search.posts.dto.PostsSaveResponse;
import com.search.taggeduser.TaggedUserService;
import com.search.typodictionary.TypoDictionaryService;
import com.search.typodictionary.dto.TypoResult;
import com.search.user.User;
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
    public PostsSaveResponse save(Long userId, String content, List<Long> taggedUserIds) {
        Posts posts = new Posts(content);
        Posts savedPosts = postsRepository.save(posts);
        User author = userService.getUser(userId);
        savedPosts.setUser(author);
        taggedUserService.linkedUserToPost(taggedUserIds, savedPosts);
        return new PostsSaveResponse(savedPosts.getId());
    }

    @Transactional
    public PostSearchResponse searchPosts(String keyword, Long userId) {
        TypoResult typoResult = typoDictionaryService.correctWords(splitKeyword(keyword));
        List<String> words = typoResult.getWords();
        List<Posts> postsList = search(words);
        boolean isCensored = false;
        if (userService.isMinor(userId)) {
            CensoredResult censoredResult = censorWordService.censorPostsList(postsList);
            postsList = censoredResult.getCensoredPosts();
            isCensored = censoredResult.isCensored();
        }
        increaseViewCount(postsList);

        List<PostsInfo> postsInfos = postsList.stream()
                .map(
                        it -> PostsInfo.builder()
                                .id(it.getId())
                                .userName(it.getUser().getName())
                                .content(it.getContent())
                                .taggedUsers(it.getTaggedUsers())
                                .build())
                .collect(Collectors.toList());

        return PostSearchResponse.builder()
                .censored(isCensored)
                .corrected(typoResult.getTypoDictionaryInfos())
                .data(postsInfos)
                .build();
    }

    private List<Posts> search(List<String> words) {
        List<Posts> allPosts = postsRepository.findAll();
        return allPosts.stream()
                .filter(it -> it.containAny(words))
                .collect(Collectors.toList());
    }

    private String[] splitKeyword(String keyword) {
        return keyword.split(" ");
    }

    private void increaseViewCount(List<Posts> postsList) {
        postsList.forEach(Posts::increaseViewcnt);
    }
}
