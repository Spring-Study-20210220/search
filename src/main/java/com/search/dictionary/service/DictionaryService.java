package com.search.dictionary.service;

import com.search.dictionary.CorrectedSentence;
import com.search.dictionary.entity.Dictionary;
import com.search.dictionary.repository.DictionaryRepository;
import com.search.post.res.CorrectionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<Dictionary> gets() {
        return dictionaryRepository.findAll();
    }

    public CorrectedSentence correct(String keyword) {
        Map<String, String> typoWordMaps = gets().stream().collect(Collectors.toMap(Dictionary::getTypo, Dictionary::getWord));

        Map<Integer, String> typoIndexMaps = typoWordMaps.keySet()
                .stream()
                .filter(keyword::contains)
                .collect(Collectors.toMap(keyword::indexOf, it -> it));

        int lastFixIndex = -1;

        String correctedKeyword = keyword;
        List<CorrectionResponse> corrections = new ArrayList();

        for (int i = 0; i < keyword.length(); i++) {
            String typo = typoIndexMaps.get(i);
            if (typo != null && lastFixIndex < i) {
                String correctWord = typoWordMaps.get(typo);
                correctedKeyword = correctedKeyword.replaceFirst(typo, correctWord);
                lastFixIndex = i + correctWord.length() - 1;
                corrections.add(new CorrectionResponse(typo, correctWord));
            }
        }

        return new CorrectedSentence(correctedKeyword, corrections);
    }

}


