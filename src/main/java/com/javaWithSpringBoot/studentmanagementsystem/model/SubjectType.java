package com.javaWithSpringBoot.studentmanagementsystem.model;
/**
 * Created by sailesh on 11/29/21.
 */
public enum SubjectType {
    ENGLISH(1, "English"),
    NEPALI(2, "Nepali"),
    SCIENCE(3, "Science"),
    MATH(4, "Math");

    private Integer key;
    private String desc;

    SubjectType(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectType lookupById(Integer key) {
        for(SubjectType subjectType : SubjectType.values()) {
            if(subjectType.getKey() == key) {
                return subjectType;
            }
        }
        return  null;
    }

    public static SubjectType lookupByDesc(String desc) {
        for(SubjectType subjectType : SubjectType.values()) {
            if(subjectType.getDesc().equals(desc)) {
                return subjectType;
            }
        }
        return  null;
    }
}

