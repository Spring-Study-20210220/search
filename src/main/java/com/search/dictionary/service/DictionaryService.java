package com.search.dictionary.service;

import com.search.dictionary.CorrectedSentence;
import com.search.dictionary.entity.Dictionary;
import com.search.dictionary.repository.DictionaryRepository;
import com.search.post.res.CorrectionResponse;
import org.springframework.stereotype.Service;
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
        //todo
        return new CorrectedSentence(null, null);
    }
}


//    List<String> toFix =  dictionaryService.gets()
//            .stream()
//            .filter(dictionary -> keyword.contains(dictionary.getTypo()))
//            .map(dictionary -> dictionary.getTypo())
//            .collect(Collectors.toList());
//
//    Map<String, String> typoMap = dictionaryService.gets()
//            .stream()
//            .filter(dictionary -> keyword.contains(dictionary.getTypo()))
//            .collect(Collectors.toMap(it -> it.getTypo(), it -> it.getWord()));
