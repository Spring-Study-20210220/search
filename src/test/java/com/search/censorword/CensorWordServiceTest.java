package com.search.censorword;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CensorWordServiceTest {
    @InjectMocks
    private CensorWordService censorWordService;
    @Mock
    private CensorWordRepository censorWordRepository;

    @Test
    void 텍스트검열테스트() {
        CensorWord censorWord1 = CensorWord.builder()
                .word("바보")
                .build();
        CensorWord censorWord2 = CensorWord.builder()
                .word("멍청이")
                .build();
        var res = Arrays.asList(censorWord1, censorWord2);
        given(censorWordRepository.findAll()).willReturn(res);

        boolean result = censorWordService.censorText("바보");

        assertThat(result).isTrue();
    }

}
