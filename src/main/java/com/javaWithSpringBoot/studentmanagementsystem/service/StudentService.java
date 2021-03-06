package com.javaWithSpringBoot.studentmanagementsystem.service;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepository;
//import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Student savedStudent = studentRepository.save(student);
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

    public Student updateStudentDetails(Student student) {
        Boolean isValid = validateStudent(student);
        if(isValid) {
//            Student existingStudent = getStudentDetails(student.getStudentId());
//            BeanUtils.copyProperties(student, existingStudent);
             return studentRepository.updateStudentDetails(student);
        }
        return  null;
    }

    public Boolean deleteStudent(Integer studentId) {
        Student student = getStudentDetails(studentId);
         studentRepository.deleteStudent(student);
        return true;
    }

    public Long getStudentCount() {
        return studentRepository.count();
    }


}



