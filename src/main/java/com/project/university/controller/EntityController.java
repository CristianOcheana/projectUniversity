package com.project.university.controller;

import com.project.university.repositories.EntityRepository;
import com.project.university.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EntityController {

    private EntityRepository entityRepository;

    @Autowired
    public EntityController(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @GetMapping("/")
    public String showUpdateForm() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Student student) {
        return "login";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<Student> studentList = entityRepository.findAll();
        model.addAttribute("students", studentList);
        return "student-list";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Student student) {
        return "sign-up";
    }

    @PostMapping("/addStudent")
    public String addUser(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sign-up";
        }
        entityRepository.save(student);
        model.addAttribute("student", entityRepository.findAll());
        return "redirect:students";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = entityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        entityRepository.delete(student);
        model.addAttribute("student", entityRepository.findAll());
        return "redirect:../students";
    }

    @GetMapping("/editStudent/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = entityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        entityRepository.save(student);
        model.addAttribute("student", entityRepository.findAll());
        return "redirect:../students";
    }


}
