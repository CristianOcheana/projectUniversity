package com.project.university.controllers.admin;

import com.project.university.controllers.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Profile extends AdminController {
    @GetMapping("profile")
    public String profile() {
        return "admin/profile";
    }
}
