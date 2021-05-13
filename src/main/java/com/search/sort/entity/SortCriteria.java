package com.search.sort.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "sort_criteria")
public class SortCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Method method;

    enum Method {
        MATCH_COUNT, VIEW_COUNT, LATEST
    }
}
