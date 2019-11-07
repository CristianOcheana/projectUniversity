package com.project.university.controllers;

import com.project.university.dto.UserDto;
import com.project.university.entityUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;


    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignUpForm(UserDto userDto) {
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model) {
        if (userService.emailExist(userDto.getEmail())) {
            result.rejectValue("email", null, "This email is already register: " + userDto.getEmail());
        }

        if (Pattern.compile("[0-9]").matcher(userDto.getFirstName()).find()) {
            result.rejectValue("firstName", null, "First Name must contain only letters");
        }

        if (Pattern.compile("[0-9]").matcher(userDto.getLastName()).find()) {
            result.rejectValue("lastName", null, "Last Name must contain only letters");
        }

        if (result.hasErrors()) {
            return "register";
        }


        userService.save(userDto);
        model.addAttribute("user", userService.findAll());
        return "redirect:users";
    }


}
