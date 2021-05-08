package com.search.user.service;

import com.search.user.entity.User;
import com.search.user.repository.UserRepository;
import com.search.user.req.SaveUserRequest;
import com.search.user.res.SaveUserResponse;
import com.search.user.res.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "name", 11);
    }

    //todo: argument 체크
    @Test
    void create() {
        // given
        given(userRepository.save(any())).willReturn(user);
        // when
        SaveUserRequest saveUserRequest = new SaveUserRequest(user.getName(), user.getAge());
        SaveUserResponse saveUser = userService.save(saveUserRequest);
        // then
        assertThat(saveUser.getName()).isEqualTo(user.getName());
    }

    @Test
    void get() {
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        UserResponse userResponse = userService.get(user.getId());

        assertThat(userResponse.getName()).isEqualTo(user.getName());
    }
}
