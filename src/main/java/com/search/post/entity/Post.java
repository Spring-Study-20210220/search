package com.search.post.entity;

import com.search.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCnt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Post(Long id, User user, String content, int viewCnt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.viewCnt = viewCnt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isContain(List<String> keywordSplits) {
        return keywordSplits.stream()
                .filter(split -> content.contains(split))
                .count() > 0;
    }
}
