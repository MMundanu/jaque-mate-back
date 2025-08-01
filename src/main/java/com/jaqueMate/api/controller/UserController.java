package com.jaqueMate.api.controller;


import com.jaqueMate.application.service.user.LoginUserService;
import com.jaqueMate.application.service.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RegisterUserService registerUserService;
    private final LoginUserService loginUserService;

    @Autowired
    public UserController(RegisterUserService registerUserService, LoginUserService loginUserService) {
        this.registerUserService = registerUserService;
        this.loginUserService = loginUserService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserService.RegisterUserRequest request) {
        registerUserService.execute(request);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserService.LoginUserRequest request) {
        return loginUserService.execute(request);
    }
}
