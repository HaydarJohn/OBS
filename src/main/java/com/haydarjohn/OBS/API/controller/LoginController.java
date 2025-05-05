package com.haydarjohn.OBS.API.controller;

import com.haydarjohn.OBS.API.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class LoginController {
    private User user;

    public LoginController(User user) {
        this.user = user;
    }


    @GetMapping("/")
    public boolean Authenticate(@RequestBody String email, @RequestBody String password)
    {
        return (user.getEmail().equals(email) && user.getPassword().equals(password));
    }



}
