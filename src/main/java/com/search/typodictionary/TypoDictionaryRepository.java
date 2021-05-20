package com.search.typodictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypoDictionaryRepository extends JpaRepository<TypoDictionary, Long> {
    public Optional<TypoDictionary> findByTypo(String typo);
}
