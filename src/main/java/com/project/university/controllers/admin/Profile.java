package com.project.university.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Profile {
    @GetMapping("/admin/profile")
    public String profile() {
        return "admin/profile";
    }
}
