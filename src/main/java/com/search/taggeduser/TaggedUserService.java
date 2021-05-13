package com.search.taggeduser;

import com.search.posts.Posts;
import com.search.user.User;
import com.search.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaggedUserService {

    private final UserRepository userRepository;

    private final TaggedUserRepository taggedUserRepository;

    @Transactional
    public void linkedUserToPost(List<Long> taggedIds, Posts posts) {

        for(Long id : taggedIds) {
            User user = userRepository.findById(id)
                    .orElseThrow(
                            () -> new IllegalArgumentException("존재하지 않는 id 입니다")
                    );
            TaggedUser taggedUser = new TaggedUser(posts, user);
            TaggedUser saved = taggedUserRepository.save(taggedUser);
            user.addTaggedUser(saved);
            posts.addTaggedUser(saved);
        }
    }
}
