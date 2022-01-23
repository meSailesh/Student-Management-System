package com.javaWithSpringBoot.studentmanagementsystem.service;


import com.javaWithSpringBoot.studentmanagementsystem.model.Result;
import com.javaWithSpringBoot.studentmanagementsystem.entity.StudentMark;
import com.javaWithSpringBoot.studentmanagementsystem.model.ResultDto;
import com.javaWithSpringBoot.studentmanagementsystem.model.SubjectType;
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

    public List<StudentMark> insertMarksForTheStudent(ResultDto resultDto)  {
        List<StudentMark> studentMarkList = new ArrayList<>();
        Map<String, Double> subjectMarksMap = resultDto.getSubjectMarks();

        if(resultDto.getSelectedStudent() == -1) {
            return  null;
        }
        for(String subject : resultDto.getSubjectMarks().keySet()) {
            Double mark = subjectMarksMap.get(subject);
            StudentMark studentMark = new StudentMark();
            studentMark.setStudentId(resultDto.getSelectedStudent());
            studentMark.setMark(mark);
            studentMark.setSubjectId(SubjectType.lookupByDesc(subject).getKey());

            studentMarkList.add(studentMark);
        }

        return  studentMarkRepository.saveAll(studentMarkList);
    }



    public List<StudentMark>  getStudentMarks(Integer studentId) {
        return studentMarkRepository.getStudentMarkByStudentId(studentId);
    }

    public List<StudentMark> getAllStudentMarks() {
        return studentMarkRepository.findAll();
    }


}
