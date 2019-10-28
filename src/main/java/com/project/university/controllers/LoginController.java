package com.project.university.controllers;

import com.project.university.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String showLoginForm(UserDto userDto) {
        return "login";
    }


}
