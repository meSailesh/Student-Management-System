package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.model.MatricsDto;
import com.javaWithSpringBoot.studentmanagementsystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by sailesh on 12/6/21.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("")
    public String viewDashboard(Model model) {
        MatricsDto matricsDto = dashboardService.getDashboardMatrics();
        model.addAttribute("matrics", matricsDto);
        return  "dashboard";
    }



}
