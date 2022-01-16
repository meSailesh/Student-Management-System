package com.javaWithSpringBoot.studentmanagementsystem.model;

/**
 * Created by sailesh on 11/26/21.
 */
public class StudentMark {

    private Integer studentMarkId;
    private Integer studentId;
    private Integer subjectId;
    private Double marks;

    @Override
    public String toString() {
        return "StudentMark{" +
                "studentMarkId=" + studentMarkId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", marks=" + marks +
                '}';
    }

    public Integer getStudentMarkId() {
        return studentMarkId;
    }

    public void setStudentMarkId(Integer studentMarkId) {
        this.studentMarkId = studentMarkId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String toCsvString() {
        return studentMarkId + "," + studentId + "," + subjectId + "," +marks;
    }
}
