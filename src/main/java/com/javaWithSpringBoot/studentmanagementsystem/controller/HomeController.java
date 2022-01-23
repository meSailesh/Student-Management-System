package com.javaWithSpringBoot.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sailesh on 1/16/22.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String viewHomePage() {
        return "redirect:/login";
    }
}
