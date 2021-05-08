package com.search.user.res;

import com.search.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private int age;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getAge());
    }
}