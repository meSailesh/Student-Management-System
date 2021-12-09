package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import com.javaWithSpringBoot.studentmanagementsystem.student.StudentService;
import com.javaWithSpringBoot.studentmanagementsystem.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/student/all")
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

    @GetMapping("/student/create")
    public String createStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {

        Student savedStudent = studentService.createStudent(student);
        if(savedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student Created successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error saving student details. Please retry!");
        return "create-student";

    }

    @GetMapping("/student/{id}")
    public String viewStudentDetails(@PathVariable(value = "id")Integer id, Model model) {
        Student student = studentService.getStudentDetails(id);
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/student/update/{id}")
    public String updateStudentDetailsPage(@PathVariable(value = "id")Integer id, Model model) {
        Student student = studentService.getStudentDetails(id);
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/student/update")
    public String updateStudentDetails(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {

        Student updatedStudent = studentService.updateStudentDetails(student);
        if(updatedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error updating student details. Please retry!");
        return "update-student";

    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id")Integer id, Model model) {

        Boolean isDeleted = studentService.deleteStudent(id);
        if(isDeleted) {
            model.addAttribute("message", "Student deleted successfully!");
        } else {
            model.addAttribute("error", "Error deleting student details. Please retry!");
        }
        return "students";

    }
}
