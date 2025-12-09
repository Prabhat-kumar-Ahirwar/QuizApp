package com.prabhat.QuizApp.controller;


import com.prabhat.QuizApp.entity.Users;
import com.prabhat.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);

    }

    @GetMapping("/")
    public String welcome(){
        return "WellCome to Quiz... ";
    }
}
