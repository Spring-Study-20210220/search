package com.search.censorword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CensorWordRepository extends JpaRepository<CensorWord, Long> {
    public Optional<CensorWord> findByWord(String word);
}
