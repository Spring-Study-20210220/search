package com.search.censorword;

import com.google.common.base.Strings;
import com.search.censorword.dto.CensoredResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CensorWordService {
    private final CensorWordRepository censorWordRepository;

    @Transactional
    public CensoredResult censorWord(List<String> words){

        List<String> censored = new ArrayList<>();
        boolean isCensored = false;
        for(String word : words) {
            Optional<CensorWord> optional = censorWordRepository.findByWord(word);
            if(optional.isEmpty()) {
                censored.add(word);
            }
            if(optional.isPresent()) {
                isCensored = true;
                censored.add(Strings.repeat("X", word.length()));
            }
        }

        return CensoredResult.builder()
                .censoredWords(censored)
                .censored(isCensored)
                .build();
    }
}
