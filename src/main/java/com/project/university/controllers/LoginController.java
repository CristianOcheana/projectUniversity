package com.project.university.controllers;

import com.project.university.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "login";
    }


}
