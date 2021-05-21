package com.search.sort.service;

import com.search.post.entity.Post;
import com.search.sort.entity.SortCriteria;
import com.search.sort.repository.SortCriteriaRepository;
import com.search.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SortCriteriaServiceTest {

    @Mock
    private SortCriteriaRepository sortCriteriaRepository;

    @InjectMocks
    private SortCriteriaService sortCriteriaService;

    private User user = new User(0L, "hi", 20);
    private List<Post> posts = List.of(
            new Post(0L,
                    user,
                    "a b c",
                    0,
                    LocalDateTime.now(),
                    LocalDate.parse("20210501", DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)).atStartOfDay()),
            new Post(1L,
                    user,
                    "a",
                    5,
                    LocalDateTime.now(),
                    LocalDate.parse("20210401", DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)).atStartOfDay()),
            new Post(2L,
                    user,
                    "a b",
                    1,
                    LocalDateTime.now(),
                    LocalDate.parse("20210701", DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREA)).atStartOfDay())
    );

    @Test
    void sortByLatest() {
        given(sortCriteriaRepository.findFirstByOrderByIdDesc()).willReturn(new SortCriteria(0L, SortCriteria.Method.LATEST));
        List<Post> sortedPosts = sortCriteriaService.sortByLatestCriteria(posts, "content");

        assertThat(sortedPosts.get(0).getId()).isEqualTo(2L);
        assertThat(sortedPosts.get(1).getId()).isEqualTo(0L);
        assertThat(sortedPosts.get(2).getId()).isEqualTo(1L);
    }

    @Test
    void sortByViewCount() {
        given(sortCriteriaRepository.findFirstByOrderByIdDesc()).willReturn(new SortCriteria(0L, SortCriteria.Method.VIEW_COUNT));
        List<Post> sortedPosts = sortCriteriaService.sortByLatestCriteria(posts, "content");

        assertThat(sortedPosts.get(0).getId()).isEqualTo(1L);
        assertThat(sortedPosts.get(1).getId()).isEqualTo(2L);
        assertThat(sortedPosts.get(2).getId()).isEqualTo(0L);
    }

    @Test
    void sortByMatchCount() {
        given(sortCriteriaRepository.findFirstByOrderByIdDesc()).willReturn(new SortCriteria(0L, SortCriteria.Method.MATCH_COUNT));
        List<Post> sortedPosts = sortCriteriaService.sortByLatestCriteria(posts, "a b c");

        assertThat(sortedPosts.get(0).getId()).isEqualTo(0L);
        assertThat(sortedPosts.get(1).getId()).isEqualTo(2L);
        assertThat(sortedPosts.get(2).getId()).isEqualTo(1L);
    }
}
