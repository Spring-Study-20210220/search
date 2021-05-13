package com.search.censor.service;

import com.search.censor.entity.CensoredWord;
import com.search.censor.repository.CensoredWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CensoredWordService {
    private final CensoredWordRepository censoredWordRepository;

    public List<String> gets() {
        CensoredWord censoredWord = censoredWordRepository.findFirstByOrderByIdDesc();
        return Arrays.asList(censoredWord.getContent().split(", "));
    }
}
