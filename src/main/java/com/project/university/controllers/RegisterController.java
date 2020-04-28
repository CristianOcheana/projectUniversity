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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model) {
        if (userDto.getFirstName().length() == 0) {
            result.rejectValue("firstName", null, "First name is required");
        } else if (userDto.getFirstName().length() < 3) {
            result.rejectValue("firstName", null, "First name must be at least 3 characters");
        } else if (!userDto.getFirstName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("firstName", null, "First Name must contain only letters");
        }

        if (userDto.getLastName().length() == 0) {
            result.rejectValue("lastName", null, "Last name is required");
        } else if (userDto.getLastName().length() < 3) {
            result.rejectValue("lastName", null, "Last name must be at least 3 characters");
        } else if (!userDto.getLastName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("lastName", null, "Last Name must contain only letters");
        }

        if (userDto.getEmail().length() == 0) {
            result.rejectValue("email", null, "The email is required");
        } else {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(userDto.getEmail());
            if (!matcher.matches()) {
                result.rejectValue("email", null, "Please enter a valid email");
            } else {

                if (userService.emailExist(userDto.getEmail())) {
                    result.rejectValue("email", null, "This email is already register: " + userDto.getEmail());
                }

                if (!checkEmail.checkEmailIfExists(userDto.getEmail())) {
                    result.rejectValue("email", null, "You are not a student of this university");
                }
            }
        }


        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            result.rejectValue("matchingPassword", null, "Confirm password incorrect");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userDto);
        model.addAttribute("user", userService.findAll());
        return "redirect:users";
    }
}
