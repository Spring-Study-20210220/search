package com.search.sort.repository;

import com.search.sort.entity.SortCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortCriteriaRepository extends JpaRepository<SortCriteria, Long> {
    public SortCriteria findFirstByOrderByIdDesc();
}
