package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.entity.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sailesh on 11/26/21.
 */

public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {

    List<StudentMark> getStudentMarkByStudentId(Integer studentId);
}
