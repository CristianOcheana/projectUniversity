package com.project.university.controllers;

import com.project.university.entities.User;
import com.project.university.repositories.user.UserService;
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

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "sign-up";
    }

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.save(user);
        model.addAttribute("user", userService.findAll());
        return "redirect:users";
    }
}
