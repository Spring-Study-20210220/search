package com.search.user.service;

import com.search.user.entity.User;
import com.search.user.repository.UserRepository;
import com.search.user.req.SaveUserRequest;
import com.search.user.res.SaveUserResponse;
import com.search.user.res.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public SaveUserResponse save(SaveUserRequest req) {
        User savedUser = userRepository.save(new User(0L, req.getName(), req.getAge()));
        return new SaveUserResponse(savedUser.getId(), savedUser.getName());
    }

    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new UserResponse(user.getId(), user.getName(), user.getAge());
    }
}
