package com.javaWithSpringBoot.studentmanagementsystem.repository.custom;

import com.javaWithSpringBoot.studentmanagementsystem.student.Student;

import java.util.List;

/**
 * Created by sailesh on 12/15/21.
 */
public interface StudentRepositoryCustom {

    Student saveStudentDetails(Student student);
    Student updateStudentDetails(Student student);
    Student getStudentDetails(Integer studentId);
    List<Student> getAllStudentDetails();
    Boolean deleteStudent(Student student);


}
