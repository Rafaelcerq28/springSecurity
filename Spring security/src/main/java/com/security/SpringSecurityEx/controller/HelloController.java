package com.security.SpringSecurityEx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        //how to get session ID (using only spring security)
        return "Hello world " + request.getSession().getId();
    }

}
