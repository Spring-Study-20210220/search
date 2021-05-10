package com.search.posts;

import com.search.posts.dto.PostsSaveResponse;
import com.search.taggeduser.TaggedUserRepository;
import com.search.taggeduser.TaggedUserService;
import com.search.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TaggedUserService taggedUserService;

    @Transactional
    public PostsSaveResponse save(String content, List<Long> taggedUserIds){
        Posts posts = new Posts(content);
        Posts savedPosts =  postsRepository.save(posts);
        taggedUserService.linkedUserToPost(taggedUserIds,savedPosts);
        return new PostsSaveResponse(savedPosts.getId());
    }
}
