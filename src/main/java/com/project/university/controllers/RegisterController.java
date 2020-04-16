package com.project.university.controllers;

import com.project.university.dto.UserDto;
import com.project.university.entityUser.UserService;
import com.project.university.userCheck.CheckEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;
    private CheckEmail checkEmail;


    @Autowired
    public RegisterController(UserService userService, CheckEmail checkEmail) {
        this.userService = userService;
        this.checkEmail = checkEmail;
    }

    @GetMapping
    public String showSignUpForm(UserDto userDto) {
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result) {
        if (userService.emailExist(userDto.getEmail())) {
            result.rejectValue("email", null, "This email is already register: " + userDto.getEmail());
        }

        if (!checkEmail.checkEmailIfExists(userDto.getEmail())) {
            result.rejectValue("email", null, "Incorrect email or You are not a student of this university");
        }

        if (!userDto.getFirstName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("firstName", null, "First Name must contain only letters");
        }

        if (!userDto.getLastName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("lastName", null, "Last Name must contain only letters");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userDto);
        return "redirect:users";
    }






}
