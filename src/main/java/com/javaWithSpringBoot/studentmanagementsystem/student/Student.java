package com.javaWithSpringBoot.studentmanagementsystem.student;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sailesh on 11/23/21.
 */
public class Student {
    private Integer studentId;
    private String name;
    private Integer age;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String gender;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String toTabularString() {
        return studentId +"\t\t" +name + "\t\t" + age + "\t\t" + gender + "\t\t" +address + "\t\t" + dob;
    }

    public String toCsvString() {
        return studentId + "," + name + "," + age + "," + gender + "," + address + "," + dob + "\n";
    }

}
