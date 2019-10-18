package com.project.university.controllers;

import com.project.university.entities.User;
import com.project.university.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    private final UserRepo userRepo;

//    @Autowired
//    public HomeController(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//        User user = new User("Bogdan", "BGD" , "BGD@test.ro", "testpassword");
//        userRepo.save(user);
//        model.addAttribute("title", "Sda University");
//        return "index";
//    }

}
