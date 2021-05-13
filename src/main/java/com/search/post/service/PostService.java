package com.search.post.service;

import com.search.censor.service.CensoredWordService;
import com.search.dictionary.CorrectedSentence;
import com.search.dictionary.service.DictionaryService;
import com.search.post.entity.Post;
import com.search.post.repository.PostRepository;
import com.search.post.req.SavePostRequest;
import com.search.post.res.SavePostResponse;
import com.search.post.res.SearchPostsResponse;
import com.search.sort.service.SortCriteriaService;
import com.search.user.entity.User;
import com.search.user.repository.UserRepository;
import com.search.user.res.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CensoredWordService censoredWordService;
    private final DictionaryService dictionaryService;
    private final SortCriteriaService sortCriteriaService;

    public SavePostResponse save(SavePostRequest savePostRequest) {
        User user = userRepository.findById(savePostRequest.getUserId()).orElseThrow(IllegalArgumentException::new);
        LocalDateTime now = LocalDateTime.now();

        Post savedPost = postRepository.save(new Post(0L, user, savePostRequest.getContent(), 0, now, now));

        return new SavePostResponse(
                savedPost.getId(),
                savedPost.getContent(),
                UserResponse.of(savedPost.getUser()));
    }

    public SearchPostsResponse search(String keyword) {
        //typo
        CorrectedSentence correctedSentence = dictionaryService.correct(keyword);
        //search keyword
        List<String> keywordSplits = Arrays.asList(correctedSentence.getValue().split(" "));
        List<Post> filteredPosts = postRepository.findAll()
                .stream()
                .filter(post -> post.isContain(keywordSplits))
                .collect(Collectors.toList());
        //censor
        List<String> censoredList = censoredWordService.gets();
        List<Post> censoredPosts = filteredPosts.stream()
                .filter(post -> post.isContain(censoredList))
                .collect(Collectors.toList());
        //filter by age

        return null;
    }


}
