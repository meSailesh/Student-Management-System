package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import com.javaWithSpringBoot.studentmanagementsystem.student.StudentService;
import com.javaWithSpringBoot.studentmanagementsystem.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by sailesh on 12/6/21.
 */
@Controller
public class HomeController {
    @Autowired
    StudentService studentService;

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
    public String viewStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
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

    @GetMapping("/create-student")
    public String createStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }

    @PostMapping("/create-student")
    public String createStudent(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {

        Student savedStudent = studentService.createStudent(student);
        if(savedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student Created successfully!");
            return "redirect:/students";
        }
        model.addAttribute("error", "Error saving student details. Please retry!");
        return "create-student";

    }
}
