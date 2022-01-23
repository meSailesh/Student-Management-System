package com.javaWithSpringBoot.studentmanagementsystem.parser;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by sailesh on 1/23/22.
 */
public class StudentDto {
    @CsvBindByName(required = true)
    private String name;
    @CsvBindByName(required = true)
    private Integer age;
    @CsvBindByName(required = true)
    private String gender;
    @CsvBindByName(required = true)
    private String address;
    @CsvBindByName(required = true)
    @CsvDate(value = "yyyy-MM-dd")

    private Date dob;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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


    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                '}';
    }

    public String format() {
        return "name,age,gender,address,dob";
    }
}
