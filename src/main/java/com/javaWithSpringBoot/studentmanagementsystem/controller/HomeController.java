package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import com.javaWithSpringBoot.studentmanagementsystem.student.StudentService;
import com.javaWithSpringBoot.studentmanagementsystem.subject.Subject;
import com.javaWithSpringBoot.studentmanagementsystem.subject.SubjectService;
import com.javaWithSpringBoot.studentmanagementsystem.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by sailesh on 12/6/21.
 */
@Controller
public class HomeController {
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectService;


    @GetMapping("/")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }



    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, Model model) {
        if(user.getEmail().equals("sailesh@gmail.com") && user.getPassword().equals("test")) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Email or password don't match.");
        return "login";
    }
    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubject();
        Integer sizes = students.size();
        Integer subjectLength = subjects.size();
//        System.out.println(sizes);
//        System.out.println(subjectLength);

        model.addAttribute("studentLength", sizes);
        model.addAttribute("subjectLength", subjectLength );
        return  "dashboard";
    }

    @GetMapping("/student/all")
    public String viewStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
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


    @GetMapping("/subject/create")
    public String createSubject(Model model){
        model.addAttribute("subject", new Subject());
        return "create-subject";

    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes)
    {

        Student savedStudent = studentService.createStudent(student);
        if(savedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student Created successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error saving student details. Please retry!");
        return "create-student";

    }

    @GetMapping("/student/{id}")
    public String viewStudentDetail(@PathVariable(value = "id")Integer studentId, Model model) {
        Student student = studentService.getStudentDetails(studentId);
        model.addAttribute("student", student);
        return "student-detail";

    }



    @GetMapping("/student/update/{id}")
    public String updateStudentDetailPage(@PathVariable(value = "id")Integer studentId, Model model) {
        Student student = studentService.getStudentDetails(studentId);
        model.addAttribute("student", student);
        return "student-update";
    }

    @PostMapping("/student/update")
    public String updateStudentDetails(@ModelAttribute("student")Student student, Model model, RedirectAttributes redirectAttributes) {
        Student updatedStudent = studentService.updateStudentDetails(student);
        if(updatedStudent != null) {
            redirectAttributes.addFlashAttribute("message", "Student updated Successfully!");
            return "redirect:/student/all";
        }
        model.addAttribute("error", "Error updating student details. Please retry!");
        return "student-update";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id")Integer studentId, RedirectAttributes redirectAttributes) {
        Boolean isDeleted= studentService.deleteStudent(studentId);
        if(isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error deleting student details. Please retry!");
        }
        return "redirect:/student/all";
    }

    @GetMapping("/subjects")
    public String viewSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubject();
        model.addAttribute("subjects", subjects);

        return "subjects";
    }

    @GetMapping("/subject/{id}")
    public String ViewSubjectDetails(@PathVariable(value = "id")Integer subjectId, Model model){
        Subject subject = subjectService.getSubjectDetails(subjectId);
        model.addAttribute("subject", subject);
        return "subject-detail";
    }



}
