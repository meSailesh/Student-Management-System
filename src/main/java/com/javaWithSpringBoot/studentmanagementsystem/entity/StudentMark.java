package com.javaWithSpringBoot.studentmanagementsystem.entity;

import javax.persistence.*;

/**
 * Created by sailesh on 11/26/21.
 */

@Entity
@Table(name = "student_mark")
public class StudentMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer studentMarkId;
    @Column(name = "student_id", nullable = false)

    private Integer studentId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "mark", nullable = false)
    private Double mark;

    @Override
    public String toString() {
        return "StudentMark{" +
                "studentMarkId=" + studentMarkId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", marks=" + mark +
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

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String toCsvString() {
        return studentMarkId + "," + studentId + "," + subjectId + "," +mark;
    }
}
