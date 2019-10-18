package com.project.university.controllers;

import com.project.university.entities.User;
import com.project.university.repositories.user.UserService;
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

    @GetMapping("/")
    public String showUpdateForm() {
        return "index";
    }

    @GetMapping("/users")
    public String getAllStudents(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users-list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        userService.delete(user);
        model.addAttribute("user", userService.findAll());
        return "redirect:../users";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/updateUser/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.save(user);
        model.addAttribute("user", userService.findAll());
        return "redirect:../users";
    }

}
