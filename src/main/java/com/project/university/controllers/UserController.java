package com.project.university.controllers;

import com.project.university.dto.UserDto;
import com.project.university.entityUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("users", userDtoList);
        return "users-list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        UserDto userDto = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        userService.delete(userDto);
        model.addAttribute("user", userService.findAll());
        return "redirect:../users";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserDto userDto = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("getUserId", userDto);
        return "update-user";
    }

    @PostMapping("/updateUser/{id}")
    public String updateStudent( @PathVariable("id") long id, @ModelAttribute("getUserId") @Valid UserDto userDto, BindingResult result, Model model) {

        if (!userDto.getFirstName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("firstName", null, "First Name is mandatory and must contains only letters");
        }

        if (!userDto.getLastName().chars().allMatch(Character::isLetter)) {
            result.rejectValue("lastName", null, "Last Name is mandatory and must contains only letters");
        }

        if (!userService.getAuthenticatedUser().getUsername().equals(userDto.getEmail())) {
            result.rejectValue("email", null, "Email cannot be modify!!!");

        }




        if (result.hasErrors()) {
            return "update-user";

        }


            userService.updateProfile(userDto);

        return "redirect:../users";
    }

    @GetMapping("/updateProfile")
    public String showUpdateForms(Model model) {
        org.springframework.security.core.userdetails.User userDetail = userService.getAuthenticatedUser();
        UserDto userDto = userService.findByEmail(userDetail.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("Invalid user" + userDetail.getUsername()));
        model.addAttribute("getUserId", userDto);
        return "update-user";

    }

//    @GetMapping("/deleteProfile")
//    public String deleteProfile(Model model) {
//        User userDetails = userService.getAuthenticatedUser();
//        UserDto userDto = userService.findByEmail(userDetails.getUsername()).orElseThrow(() ->
//                new IllegalArgumentException("Invalid user" + userDetails.getUsername()));
//        userService.delete(userDto);
//        model.addAttribute("user", userDto);
//        return "index";
//    }




}
