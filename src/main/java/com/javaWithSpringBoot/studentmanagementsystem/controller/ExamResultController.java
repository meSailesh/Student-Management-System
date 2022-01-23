package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.entity.StudentMark;
import com.javaWithSpringBoot.studentmanagementsystem.model.Result;
import com.javaWithSpringBoot.studentmanagementsystem.model.ResultDto;
import com.javaWithSpringBoot.studentmanagementsystem.service.ExamResultService;
import com.javaWithSpringBoot.studentmanagementsystem.service.StudentMarkService;
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
@RequestMapping("/result")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    @Autowired
    private StudentMarkService studentMarkService;

    @GetMapping("/all")
    public String viewResults(Model model) {
        List<Result> results = examResultService.getAllExamResults();
        model.addAttribute("results", results);

        return "result/results";
    }

    @GetMapping("/create")
    public String createResultPage(Model model) {
        ResultDto resultDto = examResultService.prepareStudentFormParams();
        model.addAttribute("resultDto", resultDto);
        return "result/create-result";
    }

    @PostMapping("/create")
    public String createResultPage(@ModelAttribute("resultDto")ResultDto resultDto,RedirectAttributes redirectAttributes) {

        //todo If marks already exist for the student on the specific subject, show popup to confirm whether they want to override previous entry

        List<StudentMark> savedMarks = studentMarkService.insertMarksForTheStudent(resultDto);
        if(savedMarks != null && !savedMarks.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Student Created successfully!");
            return "redirect:/result/all";
        }
        redirectAttributes.addFlashAttribute("error", "Error saving student marks. Please retry!");
        return "redirect:/result/create";

    }

    @GetMapping("/{id}")
    public String viewExamResult(@PathVariable(value = "id")Integer studentId, Model model) {
        Result result = examResultService.viewExamResult(studentId);
        model.addAttribute("result", result);
        return "result/exam-result";
    }



}
