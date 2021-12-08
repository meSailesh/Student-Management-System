package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.studentMarks.StudentMark;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sailesh on 11/26/21.
 */

public interface StudentMarkRepository {

    List<StudentMark> createStudentMark(List<StudentMark> studentMarkList);

    List<StudentMark> getStudentMark(Integer studentId);
}
