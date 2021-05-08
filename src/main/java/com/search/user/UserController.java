package com.search.user;

import com.search.user.dto.UserRequest;
import com.search.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    ResponseEntity<UserResponse> saveUser(UserRequest userDto) {
        UserResponse userResponse = userService.saveUser(
                userDto.getName(),
                userDto.getAge());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }
}
