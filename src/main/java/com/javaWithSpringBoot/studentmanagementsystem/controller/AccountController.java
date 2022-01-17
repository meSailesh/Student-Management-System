package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.entity.User;
import com.javaWithSpringBoot.studentmanagementsystem.model.UserDto;
import com.javaWithSpringBoot.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by sailesh on 1/16/22.
 */

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto user, RedirectAttributes redirectAttributes, Model model) {
        try {
            userService.registerUser(user);

            String successMessage = "You have signed up successfully, " + user.getFirstName() + "! Please login to continue..";
            redirectAttributes.addFlashAttribute("message", successMessage);
            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }

}
