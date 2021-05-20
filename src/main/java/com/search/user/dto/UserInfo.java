package com.search.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {
    private Long userId;
    private String userName;

    @Builder
    public UserInfo(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
