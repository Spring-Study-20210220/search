package com.search.user.controller;

import com.search.user.req.SaveUserRequest;
import com.search.user.res.SaveUserResponse;
import com.search.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public SaveUserResponse save(SaveUserRequest saveUserRequest) {
        return userService.save(saveUserRequest);
    }
}
