package com.search.sort;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity(name = "sort_criteria")
public class SortCriteria {
    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Method method;

    enum Method {
        MATCH_COUNT, VIEW_COUNT, LATEST
    }
}
