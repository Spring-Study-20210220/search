package com.search.censorword;

import com.google.common.base.Strings;
import com.search.censorword.dto.CensoredResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CensorWordService {
    private final CensorWordRepository censorWordRepository;

    @Transactional
    public CensoredResult censorWord(List<String> words){

        boolean isCensored = false;
        for(String word : words) {
            Optional<CensorWord> optional = censorWordRepository.findByWord(word);
            if(optional.isPresent()) {
                isCensored = true;
                break;
            }
        }

        List<String> censored = censorWordRepository.findAll().stream()
                                                .map(CensorWord::getWord)
                                                .collect(Collectors.toList());

        return CensoredResult.builder()
                .censoredWords(censored)
                .censored(isCensored)
                .build();
    }
}
