package com.javaWithSpringBoot.studentmanagementsystem.model;

/**
 * Created by sailesh on 1/16/22.
 */
public class MatricsDto {
    private Long studentCount = 0L;
    private Long subjectCount = 4L;
    private Long resultCount = 0L;

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public Long getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(Long subjectCount) {
        this.subjectCount = subjectCount;
    }

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }
}
