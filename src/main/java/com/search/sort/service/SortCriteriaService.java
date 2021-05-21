package com.search.sort.service;

import com.search.post.entity.Post;
import com.search.sort.entity.SortCriteria;
import com.search.sort.repository.SortCriteriaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortCriteriaService {
    private final SortCriteriaRepository sortCriteriaRepository;

    public SortCriteriaService(SortCriteriaRepository sortCriteriaRepository) {
        this.sortCriteriaRepository = sortCriteriaRepository;
    }

    private SortCriteria latest() {
        return sortCriteriaRepository.findFirstByOrderByIdDesc();
    }

    public List<Post> sortByLatestCriteria(List<Post> filteredPosts, String keywordSentence) {
        SortCriteria criteria = latest();

        if (criteria.getMethod() == SortCriteria.Method.VIEW_COUNT) {
            return filteredPosts.stream()
                    .sorted(Comparator.comparing(Post::getViewCnt).reversed())
                    .collect(Collectors.toList());
        }

        if (criteria.getMethod() == SortCriteria.Method.LATEST) {
            return filteredPosts.stream()
                    .sorted(Comparator.comparing(Post::getUpdatedAt).reversed())
                    .collect(Collectors.toList());
        }

        return filteredPosts.stream()
                .sorted((a, b) -> b.getEncounterKeywordCnt(keywordSentence) - a.getEncounterKeywordCnt(keywordSentence))
                .collect(Collectors.toList());
    }
}
