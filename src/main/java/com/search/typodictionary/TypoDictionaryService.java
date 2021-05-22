package com.search.typodictionary;

import com.search.typodictionary.dto.TypoDictionaryInfo;
import com.search.typodictionary.dto.TypoResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TypoDictionaryService {
    private final TypoDictionaryRepository typoDictionaryRepository;

    public TypoResult correctWords(String[] keywords) {
        List<TypoDictionaryInfo> typoInfos = new ArrayList<>();
        List<String> correctedWords = new ArrayList<>();
        for (String keyword : keywords) {
            Optional<TypoDictionary> typo = typoDictionaryRepository.findByTypo(keyword);
            typo.ifPresentOrElse(
                    it -> {
                        typoInfos.add(TypoDictionaryInfo.from(it));
                        correctedWords.add(it.getWord());
                    },
                    () -> correctedWords.add(keyword)
            );
        }
        return TypoResult.builder()
                .words(correctedWords)
                .typoDictionaryInfos(typoInfos)
                .build();
    }

}

