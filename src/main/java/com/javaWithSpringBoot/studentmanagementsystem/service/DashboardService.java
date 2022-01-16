package com.javaWithSpringBoot.studentmanagementsystem.service;

import com.javaWithSpringBoot.studentmanagementsystem.model.MatricsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sailesh on 1/16/22.
 */
@Service
public class DashboardService {

    @Autowired
    StudentService studentService;

    public MatricsDto getDashboardMatrics() {
        MatricsDto matricsDto = new MatricsDto();

        Long studentCount = studentService.getStudentCount();
        matricsDto.setStudentCount(studentCount);

        return  matricsDto;
    }


}
