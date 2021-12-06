package com.javaWithSpringBoot.studentmanagementsystem.repository;




import studentMarks.StudentMark;

import java.util.List;

/**
 * Created by sailesh on 11/26/21.
 */
public interface StudentMarkRepository {

    List<StudentMark> createStudentMark(List<StudentMark> studentMarkList);

    List<StudentMark> getStudentMark(Integer studentId);
}
