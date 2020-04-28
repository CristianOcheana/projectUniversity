package com.project.university.controllers.admin;

import com.project.university.controllers.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Dashboard extends AdminController {

    @GetMapping("/")
    public String index() {
        return dashboard();
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }
}
