package com.javaWithSpringBoot.studentmanagementsystem.repository;


import com.javaWithSpringBoot.studentmanagementsystem.repository.custom.StudentRepositoryCustom;
import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sailesh on 11/23/21.
 */
public interface StudentRepository extends JpaRepository<Student, Integer>, StudentRepositoryCustom{
}

