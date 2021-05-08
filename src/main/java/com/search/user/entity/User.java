package com.search.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public User(Long id, String name, int age) {
        validateArguments(age);
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private void validateArguments(int age) {
        if(age < 0) {
            throw new IllegalArgumentException();
        }
    }
}
