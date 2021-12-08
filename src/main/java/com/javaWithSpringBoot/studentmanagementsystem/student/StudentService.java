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

    public Student createStudent(Student student) {

        Boolean isValid = validateStudent(student);
        if(isValid) {
            Student savedStudent = studentRepository.saveStudentDetails(student);
            return  savedStudent;
        }
        return null;
    }

    private Boolean validateStudent(Student student) {
            if(student == null || student.getName().isEmpty() || student.getAddress().isEmpty() || student.getAge() == null || student.getDob() == null || student.getGender().isEmpty()) {
                return false;
            }

        return true;

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



