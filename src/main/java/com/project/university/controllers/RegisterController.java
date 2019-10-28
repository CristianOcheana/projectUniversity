package com.project.university.controllers;

import com.project.university.dto.UserDto;
import com.project.university.entityUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserService userService;


    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showSignUpForm(UserDto userDto) {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(userDto);
        model.addAttribute("user", userService.findAll());
        return "redirect:users";
    }


}
