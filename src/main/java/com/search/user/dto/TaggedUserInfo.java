package com.search.user.dto;

import com.search.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaggedUserInfo {
    private Long userId;
    private String userName;

    @Builder
    public TaggedUserInfo(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public static TaggedUserInfo from(User user) {
        return TaggedUserInfo.builder()
                .userName(user.getName())
                .userId(user.getId())
                .build();
    }
}
