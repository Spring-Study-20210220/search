package com.search.typodictionary;

import com.search.typodictionary.dto.TypoDictionaryInfo;
import com.search.typodictionary.dto.TypoResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TypoDictionaryServiceTest {
    @InjectMocks
    private TypoDictionaryService typoDictionaryService;
    @Mock
    private TypoDictionaryRepository typoDictionaryRepository;

    @Test
    void 검색어교정테스트() {
        //when
        String[] keywords = {"스프링", "테스트"};
        TypoDictionary typoDictionary1 = TypoDictionary.builder()
                .typo("스프링")
                .word("파이썬")
                .build();
        TypoDictionaryInfo typoDictionaryInfo1 = TypoDictionaryInfo.builder()
                .from("스프링")
                .to("파이썬")
                .build();
        TypoDictionary typoDictionary2 = TypoDictionary.builder()
                .typo("테스트")
                .word("토스트")
                .build();
        TypoDictionaryInfo typoDictionaryInfo2 = TypoDictionaryInfo.builder()
                .from("테스트")
                .to("토스트")
                .build();
        //given
        given(typoDictionaryRepository.findByTypo(any()))
                .willReturn(Optional.of(typoDictionary1))
                .willReturn(Optional.of(typoDictionary2));
        //then
        TypoResult result = typoDictionaryService.correctWords(keywords);

        assertThat(result.getTypoDictionaryInfos()).filteredOn("from", "스프링")
                .containsOnly(typoDictionaryInfo1);
        assertThat(result.getTypoDictionaryInfos()).filteredOn("from", "테스트")
                .containsOnly(typoDictionaryInfo2);
    }
}
