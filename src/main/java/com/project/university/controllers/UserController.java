package com.project.university.controllers;


import com.project.university.entities.User;
import com.project.university.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registeruser")
    public String showRegisterForm(User user) {
        return "user-register";
    }

    @PostMapping("/registeruser")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-register";
        }
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/loginuser")
    public String showLoginForm(User user) {
        return "user-login";
    }

    @PostMapping("/loginuser")
    public String loginUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-login";
        }
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("size", users.size());
        return "users";
    }

    @GetMapping("/edituser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "users";
    }
    @PostMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-edit";
        }

        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

}
