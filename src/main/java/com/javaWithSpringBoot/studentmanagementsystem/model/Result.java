package com.javaWithSpringBoot.studentmanagementsystem.model;

import java.util.List;

/**
 * Created by sailesh on 11/29/21.
 */
public class Result {

    private List<StudentMark> studentMarkList;
    private Double totalMarks;
    private Double percentage;
    private Boolean status;

    public List<StudentMark> getStudentMarkList() {
        return studentMarkList;
    }

    public void setStudentMarkList(List<StudentMark> studentMarkList) {
        this.studentMarkList = studentMarkList;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "studentMarkList=" + studentMarkList +
                ", totalMarks=" + totalMarks +
                ", percentage=" + percentage +
                ", status=" + status +
                '}';
    }
}
