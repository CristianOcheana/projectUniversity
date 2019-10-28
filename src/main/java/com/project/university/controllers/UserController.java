package com.project.university.controllers;

import com.project.university.dto.UserDto;
import com.project.university.entityUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
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
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("users", userDtoList);
        return "users-list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        UserDto user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        userService.delete(user);
        model.addAttribute("user", userService.findAll());
        return "redirect:../users";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserDto user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/updateUser/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            userDto.setId(id);
            return "update-user";
        }
        userService.save(userDto);
        model.addAttribute("user", userService.findAll());
        return "redirect:../users";
    }

    @GetMapping("/updateProfile")
    public String showUpdateForms(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        org.springframework.security.core.userdetails.User principal =
//                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        org.springframework.security.core.userdetails.User userDetail = userService.getAuthenticatedUser();
        UserDto userDto = userService.findByEmail(userDetail.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("Invalid user" + userDetail.getUsername()));
        model.addAttribute("user", userDto);
        return "update-user";
    }



}
