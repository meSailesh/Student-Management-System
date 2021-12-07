package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by sailesh on 12/6/21.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/dashboard")
    public String viewDashboard() {
        return  "dashboard";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, Model model) {
        if(user.getEmail().equals("sailesh@gmail.com") && user.getPassword().equals("test")) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Email or password don't match.");
        return "login";
    }

    @GetMapping("/students")
    public String viewStudents() {
        return "students";
    }

    @GetMapping("/subjects")
    public String viewSubjects() {
        return "subjects";
    }

    @GetMapping("/results")
    public String viewResults() {
        return "results";
    }
}
