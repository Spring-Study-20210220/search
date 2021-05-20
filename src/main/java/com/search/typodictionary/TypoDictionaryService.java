package com.search.typodictionary;

import com.search.typodictionary.dto.TypoDictionaryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TypoDictionaryService {
    private final TypoDictionaryRepository typoDictionaryRepository;

    public List<TypoDictionaryInfo> checkWords(String[] keywords){

        List<TypoDictionary> list = new ArrayList<>();
        for(String keyword : keywords) {
            Optional<TypoDictionary> optional = typoDictionaryRepository.findByTypo(keyword);
            optional.ifPresent(list::add);
        }

        return list.stream()
                .map(it -> new TypoDictionaryInfo(it.getTypo(), it.getWord()))
                .collect(Collectors.toList());
    }

}
