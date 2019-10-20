package com.project.university.controllers;


import com.project.university.entities.User;
import com.project.university.repo.user.UserService;
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

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginUser")
    public String showLoginForm(User user) {
        return "user-login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-login";
        }
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "index";
    }


    @GetMapping("/usersList")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("size", users.size());
        return "users-list";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-edit";
        }
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "users-list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "users-list";
    }


}
