package com.javaWithSpringBoot.studentmanagementsystem.repository;

//import com.javaWithSpringBoot.studentmanagementsystem.studentMarks.StudentMark;
import com.javaWithSpringBoot.studentmanagementsystem.studentResult.Result;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentMarkRepository extends JpaRepository<Result, Long> {

//    List<StudentMark> createStudentMark(List<StudentMark> studentMarkList);
//
//    List<StudentMark> getStudentMark(Integer studentId);


}
