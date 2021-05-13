package com.search.sort.service;

import com.search.sort.entity.SortCriteria;
import com.search.sort.repository.SortCriteriaRepository;
import org.springframework.stereotype.Service;

@Service
public class SortCriteriaService {
    private final SortCriteriaRepository sortCriteriaRepository;

    public SortCriteriaService(SortCriteriaRepository sortCriteriaRepository) {
        this.sortCriteriaRepository = sortCriteriaRepository;
    }

    public SortCriteria get() {
        return sortCriteriaRepository.findFirstByOrderByIdDesc();
    }
}
