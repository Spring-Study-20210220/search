package com.search.user;

import com.search.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse saveUser(String name, Integer age) {
        User user = User.builder()
                .name(name)
                .age(age)
                .build();
        User saveUser = userRepository.save(user);
        return new UserResponse(saveUser.getId());
    }

    public boolean isMinor(Long id) {
        User user = getUser(id);
        return user.getAge() < 20;
    }

    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 id"));
    }
}
