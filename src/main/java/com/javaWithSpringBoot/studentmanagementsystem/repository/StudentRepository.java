package com.javaWithSpringBoot.studentmanagementsystem.repository;


import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
@Repository
public interface StudentRepository {

    Student saveStudentDetails(Student student);
    Student updateStudentDetails(Student student);
    Student getStudentDetails(Integer studentId);
    List<Student> getAllStudentDetails();
    Boolean deleteStudent(Integer studentId);
}

