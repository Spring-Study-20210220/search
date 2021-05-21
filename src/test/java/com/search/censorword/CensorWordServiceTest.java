package com.search.censorword;

import com.search.censorword.dto.CensoredResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CensorWordServiceTest {

    @InjectMocks
    private CensorWordService censorWordService;
    @Mock
    private CensorWordRepository censorWordRepository;


    @Test
    void 필터링서비스테스트(){
        //given
        CensorWord censorWord = CensorWord.builder()
                .word("바보")
                .build();
        List<String> words = Arrays.asList("바보","퍼보","스프링");

        given(censorWordRepository.findByWord("바보")).willReturn(Optional.of(censorWord));
        given(censorWordRepository.findAll()).willReturn(Collections.singletonList(censorWord));

        //when
        CensoredResult result = censorWordService.censorWord(words);

        //then
        assertThat(result.isCensored()).isEqualTo(true);
        assertThat(result.getCensoredWords()).contains("바보");
    }
}
