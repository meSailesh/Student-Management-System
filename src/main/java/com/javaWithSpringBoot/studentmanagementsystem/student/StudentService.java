package com.javaWithSpringBoot.studentmanagementsystem.student;

import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepository;
import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(String name, String address, String gender, Date dob, Integer age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setAddress(address);
        student.setGender(gender);
        student.setDob(dob);

        Student savedStudent = studentRepository.saveStudentDetails(student);
        return  savedStudent;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudentDetails();
    }

    public Student getStudentDetails(Integer studentId) {
        return studentRepository.getStudentDetails(studentId);
    }

    public Student updateStudentDetails(Integer studentId, String name, String address, String gender, Date dob, Integer age) {
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        student.setAge(age);
        student.setAddress(address);
        student.setGender(gender);
        student.setDob(dob);
        return  studentRepository.updateStudentDetails(student);
    }

    public Boolean deleteStudent(Integer studentId) {
        return studentRepository.deleteStudent(studentId);
    }


}



