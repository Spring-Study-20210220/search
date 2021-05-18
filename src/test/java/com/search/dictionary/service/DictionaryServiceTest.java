package com.search.dictionary.service;

import com.search.dictionary.CorrectedSentence;
import com.search.dictionary.entity.Dictionary;
import com.search.dictionary.repository.DictionaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DictionaryServiceTest {
    @Mock
    private DictionaryRepository dictionaryRepository;

    @InjectMocks
    private DictionaryService dictionaryService;

    private List<Dictionary> dictionaries;

    @Test
    void correctNotOverlappingTypos() {
        dictionaries = List.of(
                new Dictionary(0L, "시러", "싫어"),
                new Dictionary(1L, "넘무", "너무")
        );

        given(dictionaryRepository.findAll()).willReturn(dictionaries);

        CorrectedSentence correctedSentence = dictionaryService.correct("니가 넘무 시러");
        assertThat(correctedSentence.getValue()).isEqualTo("니가 너무 싫어");
    }

    @Test
    void correctOverlappingTypos(){
        dictionaries = List.of(
                new Dictionary(0L, "ab", "aa"),
                new Dictionary(1L, "bc", "bb")
        );

        given(dictionaryRepository.findAll()).willReturn(dictionaries);

        CorrectedSentence correctedSentence = dictionaryService.correct("abcef");
        assertThat(correctedSentence.getValue()).isEqualTo("aacef");
    }
}
