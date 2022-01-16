package com.javaWithSpringBoot.studentmanagementsystem.repository;


import com.javaWithSpringBoot.studentmanagementsystem.repository.custom.StudentRepositoryCustom;
import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
public interface StudentRepository extends JpaRepository<Student, Integer>, StudentRepositoryCustom{

//    List<Student> findAll();
//
//    Student findStudentByAddressAndAge(String address, Integer age);
//

}

