package com.search.censor.service;

import com.search.censor.entity.CensoredWord;
import com.search.censor.repository.CensoredWordRepository;
import com.search.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CensoredWordService {
    private final CensoredWordRepository censoredWordRepository;

    public List<String> gets() {
        CensoredWord censoredWord = censoredWordRepository.findFirstByOrderByIdDesc();
        return Arrays.asList(censoredWord.getContent().split(", "));
    }

    public List<Post> censor(List<Post> posts) {
        List<String> censoredList = gets();
        return posts.stream()
                .filter(post -> !post.isContain(censoredList))
                .collect(Collectors.toList());
    }
}
