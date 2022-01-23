package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by sailesh on 1/16/22.
 */

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public String viewStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/students";
    }

    @GetMapping("/create")
    public String createStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {

        Student savedStudent = studentService.createStudent(student);
        if(savedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student Created successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error saving student details. Please retry!");
        return "student/create-student";

    }

    @GetMapping("/{id}")
    public String viewStudentDetail(@PathVariable(value = "id")Integer studentId, Model model) {
        Student student = studentService.getStudentDetails(studentId);
        model.addAttribute("student", student);
        return "student/student-detail";
    }

    @GetMapping("/update/{id}")
    public String updateStudentDetailPage(@PathVariable(value = "id")Integer studentId, Model model) {
        Student student = studentService.getStudentDetails(studentId);
        model.addAttribute("student", student);
        return "student/student-update";
    }

    @PostMapping("/update")
    public String updateStudentDetails(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {
        Student updatedStudent = studentService.updateStudentDetails(student);
        if(updatedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student updated Successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error updating student details. Please retry!");
        return "student/student-update";
    }

    @GetMapping("/student/{id}")
    public String deleteStudent(@PathVariable(value = "id")Integer studentId, RedirectAttributes redirectAttributes) {
        Boolean isDeleted= studentService.deleteStudent(studentId);
        if(isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error deleting student details. Please retry!");
        }
        return "redirect:/student/all";
    }

}
