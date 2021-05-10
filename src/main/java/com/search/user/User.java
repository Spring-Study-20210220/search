package com.search.user;

import com.search.taggeduser.TaggedUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TaggedUser> taggedUsers = new ArrayList<>();

    @Builder
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void addTaggedUser(TaggedUser taggedUser){
        taggedUsers.add(taggedUser);
    }
}
