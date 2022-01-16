package com.javaWithSpringBoot.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sailesh on 1/16/22.
 */
@Controller
public class ExamResultController {

    @GetMapping("/results")
    public String viewResults() {
        return "results";
    }


}
