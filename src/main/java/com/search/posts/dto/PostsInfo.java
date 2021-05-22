package com.search.posts.dto;

import com.search.taggeduser.TaggedUser;
import com.search.user.dto.TaggedUserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsInfo {
    private Long id;
    private String userName;
    private String content;
    private List<TaggedUserInfo> taggedUsers;

    @Builder
    public PostsInfo(Long id, String userName, String content, List<TaggedUser> taggedUsers) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.taggedUsers = taggedUsers.stream()
                .map(taggedUser -> TaggedUserInfo.from(taggedUser.getUser()))
                .collect(Collectors.toList());
    }
}
