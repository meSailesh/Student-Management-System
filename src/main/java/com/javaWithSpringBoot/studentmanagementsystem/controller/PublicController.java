package com.javaWithSpringBoot.studentmanagementsystem.controller;

import com.javaWithSpringBoot.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sailesh on 1/23/22.
 * Contains publicly available api endpoints
 */

@RestController
@RequestMapping("/api/v1")
public class PublicController {

    @Autowired
    StudentService studentService;

    @PostMapping("/upload-student")
    @ResponseBody
    public ResponseEntity uploadStudentFile(@RequestParam("file")MultipartFile file) throws Exception{
        studentService.saveStudentsFromFile(file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/download-student")
    public void downloadStudentFile(HttpServletResponse response) throws Exception{
        studentService.exportStudentFile(response);
    }

    @GetMapping("/download-student-dummy")
    public void downloadStudentDummyFile(HttpServletResponse response) throws Exception{
        studentService.exportDummyStudentFile(response);
    }


}
