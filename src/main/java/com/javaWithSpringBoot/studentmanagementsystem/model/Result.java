package com.javaWithSpringBoot.studentmanagementsystem.model;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.entity.StudentMark;

import java.util.List;
import java.util.Map;

/**
 * Created by sailesh on 11/29/21.
 */
public class Result {

    private Student student;
    private Map<String,StudentMark> subjectMarkMap;
    private Double totalMarks;
    private Double percentage;
    private ResultStatus status;

    public Map<String, StudentMark> getSubjectMarkMap() {
        return subjectMarkMap;
    }

    public void setSubjectMarkMap(Map<String, StudentMark> subjectMarkMap) {
        this.subjectMarkMap = subjectMarkMap;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Result{" +
                "student=" + student +
                ", subjectMarkMap=" + subjectMarkMap +
                ", totalMarks=" + totalMarks +
                ", percentage=" + percentage +
                ", status=" + status +
                '}';
    }
}
