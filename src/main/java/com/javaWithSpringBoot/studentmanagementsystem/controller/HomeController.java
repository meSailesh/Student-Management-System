package com.javaWithSpringBoot.studentmanagementsystem.controller;

import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
