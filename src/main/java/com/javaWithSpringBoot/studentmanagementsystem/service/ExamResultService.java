package com.javaWithSpringBoot.studentmanagementsystem.service;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.entity.StudentMark;
import com.javaWithSpringBoot.studentmanagementsystem.model.Result;
import com.javaWithSpringBoot.studentmanagementsystem.model.ResultDto;
import com.javaWithSpringBoot.studentmanagementsystem.model.ResultStatus;
import com.javaWithSpringBoot.studentmanagementsystem.model.SubjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sailesh on 1/16/22.
 */
@Service
public class ExamResultService {
    private static final Integer PASS_MARK = 32;

    @Autowired
    StudentMarkService studentMarkService;

    @Autowired
    StudentService studentService;

    public ResultDto prepareStudentFormParams() {
        ResultDto resultDto = new ResultDto();
        List<Student> studentList = studentService.getAllStudents();
        Map<Integer, String> studentMap = studentList.stream().collect(Collectors.toMap(Student::getStudentId, Student::getName));
        resultDto.setStudents(studentMap);
        Map<String, Double> subjectMarkMap = new HashMap<>();
        for(SubjectType subjectType: SubjectType.values()) {
            subjectMarkMap.put(subjectType.getDesc(), null);
        }
        resultDto.setSubjectMarks(subjectMarkMap);
        return  resultDto;

    }

    public List<Result> getAllExamResults() {
        List<StudentMark> studentMarkList = studentMarkService.getAllStudentMarks();
        List<Result> results = new ArrayList<>();
        if(studentMarkList != null && !studentMarkList.isEmpty()) {
            Map<Integer, List<StudentMark>> studentMarkMap = studentMarkList.stream().collect(Collectors.groupingBy(StudentMark::getStudentId));
            for(Integer studentId : studentMarkMap.keySet()) {
                Result result = new Result();
                Map<String, StudentMark> subjectMarkMap = new HashMap<>();
                for(StudentMark studentMark : studentMarkMap.get(studentId)) {
                    String subjectName = SubjectType.lookupById(studentMark.getSubjectId()).getDesc();
                    subjectMarkMap.put(subjectName, studentMark);
                }
                result.setSubjectMarkMap(subjectMarkMap);
                Student student = studentService.getStudentDetails(studentId);
                result.setStudent(student);
                setAggregateData(result);

                results.add(result);
            }
        }
        return  results;
    }

    public Result viewExamResult(Integer studentId) {
        List<StudentMark> studentMarkList = studentMarkService.getStudentMarks(studentId);
        if(studentMarkList != null && !studentMarkList.isEmpty()) {
            Result result = new Result();
            Map<String, StudentMark> studentMarkMap = new HashMap<>();
            for(StudentMark studentMark : studentMarkList) {
                String subjectName = SubjectType.lookupById(studentMark.getSubjectId()).getDesc();
                studentMarkMap.put(subjectName, studentMark);
            }
            result.setSubjectMarkMap(studentMarkMap);
            Student student = studentService.getStudentDetails(studentId);
            result.setStudent(student);
            setAggregateData(result);
            return  result;
        }
        return  null;
    }

    private void setAggregateData(Result result) {
        Map<String, StudentMark> subjectMarkMap = result.getSubjectMarkMap();
        Double totalMarksObtained = 0D;
        Integer totalMarks = subjectMarkMap.size()*100;

        ResultStatus status = ResultStatus.PASS;

        for(StudentMark studentMark : subjectMarkMap.values()) {
            totalMarksObtained += studentMark.getMark();
            if(studentMark.getMark() < PASS_MARK) {
                status = ResultStatus.FAIL;
            }
        }

        result.setTotalMarks(totalMarksObtained);

        Double percent = totalMarksObtained/totalMarks*100;
        result.setPercentage(percent);
        result.setStatus(status);
    }
}
