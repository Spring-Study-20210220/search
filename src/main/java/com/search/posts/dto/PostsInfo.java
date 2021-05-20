package com.search.posts.dto;

import com.search.user.dto.UserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsInfo {
    private Long id;
    private String userName;
    private String content;
    private List<UserInfo> taggedUsers;

    @Builder
    public PostsInfo(Long id, String userName, String content, List<UserInfo> taggedUsers) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.taggedUsers = taggedUsers;
    }
}
