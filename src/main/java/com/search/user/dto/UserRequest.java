package com.search.user.dto;

import com.search.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequest {

    private String name;

    private Integer age;

    @Builder
    public UserRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .age(age)
                .build();
    }
}
