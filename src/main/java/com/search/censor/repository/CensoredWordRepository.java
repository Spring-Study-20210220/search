package com.search.censor.repository;

import com.search.censor.entity.CensoredWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CensoredWordRepository extends JpaRepository<CensoredWord, Long> {

    public CensoredWord findFirstByOrderByIdDesc();
}
