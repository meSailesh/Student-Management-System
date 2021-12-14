package com.javaWithSpringBoot.studentmanagementsystem.repository;


import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
public interface StudentRepository extends CrudRepository<Student, Integer>{

    List<Student> findAll();

    Student findStudentByAddressAndAge(String address, Integer age);
//
//    Student saveStudentDetails(Student student);
//    Student updateStudentDetails(Student student);
//    Student getStudentDetails(Integer studentId);
//    List<Student> getAllStudentDetails();
//    Boolean deleteStudent(Integer studentId);
}

