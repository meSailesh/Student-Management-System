package com.javaWithSpringBoot.studentmanagementsystem.service;


import com.javaWithSpringBoot.studentmanagementsystem.model.Result;
import com.javaWithSpringBoot.studentmanagementsystem.model.StudentMark;
import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sailesh on 11/26/21.
 */
@Service
public class StudentMarkService {

    @Autowired
    StudentMarkRepository studentMarkRepository;

    private static final Integer PASS_MARK = 40;
    public List<StudentMark> insertMarksForTheStudent(Integer studentId, Map<Integer, Double> subjectMarksMap)  {
        List<StudentMark> studentMarkList = new ArrayList<>();
        for(Integer subjectId : subjectMarksMap.keySet()) {
            Double mark = subjectMarksMap.get(subjectId);
            StudentMark studentMark = new StudentMark();
            studentMark.setStudentId(studentId);
            studentMark.setMarks(mark);
            studentMark.setSubjectId(subjectId);

            studentMarkList.add(studentMark);
        }

        return  studentMarkRepository.createStudentMark(studentMarkList);
    }

    public Result createExamResult(Integer studentId) {
        List<StudentMark> studentMarkList = getStudentMarks(studentId);
        if(studentMarkList != null && !studentMarkList.isEmpty()) {
            Result result = new Result();

            result.setStudentMarkList(studentMarkList);

            Double totalMarksObtained = 0D;
            Integer totalMarks = studentMarkList.size()*100;

            Boolean status = true;

            for(StudentMark studentMark : studentMarkList) {
                totalMarksObtained += studentMark.getMarks();
                if(studentMark.getMarks() < PASS_MARK) {
                    status = false;
                }
            }

            result.setTotalMarks(totalMarksObtained);

            Double percent = totalMarksObtained/totalMarks*100;
            result.setPercentage(percent);
            result.setStatus(status);

            return  result;
        }
        return  null;
    }

    public List<StudentMark> getStudentMarks(Integer studentId) {
        return studentMarkRepository.getStudentMark(studentId);
    }
}
