package com.security.SpringSecurityEx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurityEx.Model.Users;
import com.security.SpringSecurityEx.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);    
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {

        return userService.verify(user);
    }

}
