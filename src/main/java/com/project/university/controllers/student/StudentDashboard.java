package com.project.university.controllers.student;

import com.project.university.controllers.StudentController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboard extends StudentController {

    @GetMapping("/")
    public String index() {
        return dashboard();
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "student/dashboard";
    }
}
