package com.search.posts;

import com.search.taggeduser.TaggedUser;
import com.search.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int viewcnt=0;

    private String content;

    @CreatedDate
    private LocalDate created_at;

    @LastModifiedDate
    private LocalDate updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<TaggedUser> taggedUsers = new LinkedList<>();

    @Builder
    public Posts(String content) {
        this.content = content;
    }

    public void addTaggedUser(TaggedUser taggedUser){
        taggedUsers.add(taggedUser);
    }
}
