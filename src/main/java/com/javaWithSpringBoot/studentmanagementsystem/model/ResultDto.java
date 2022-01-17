package com.javaWithSpringBoot.studentmanagementsystem.model;

import java.util.Map;

/**
 * Created by sailesh on 1/16/22.
 */
public class ResultDto {
    private Map<Integer, String> students;
    private Integer selectedStudent;
    private Map<String, Double> subjectMarks;


    public Map<Integer, String> getStudents() {
        return students;
    }

    public void setStudents(Map<Integer, String> students) {
        this.students = students;
    }

    public Integer getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Integer selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Map<String, Double> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(Map<String, Double> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }
}
