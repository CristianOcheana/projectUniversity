package com.project.university.controllers;

import com.project.university.entities.User;
import com.project.university.repo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    private UserService userService;

//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//
//    }

    @GetMapping("/loginUser")
    public String showLoginForm(User user) {
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/loginUser";
        }
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "users-list";
    }

    @GetMapping("/logoutUser")
    public String logoutPage() {
        return "logout";
    }
}
