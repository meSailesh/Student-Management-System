package com.javaWithSpringBoot.studentmanagementsystem.student;

import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return studentRepository.findAll();
    }

    public Student getStudentDetails(Integer studentId) {

        return studentRepository.findById(studentId).get();
    }


//(Student student) contain new value
    public Student updateStudentDetails(Student student) {
        Boolean isValid = validateStudent(student);
        if(isValid) {
            Student existingStudent = getStudentDetails(student.getStudentId());
            //manually
            // existingStudent.setName (student.getName())

            //update
            BeanUtils.copyProperties(student, existingStudent);
            return studentRepository.save(student);

        }
        return  null;
    }

    public Boolean deleteStudent(Integer studentId) {
        Student student = getStudentDetails(studentId);
        studentRepository.delete(student);
        return true;
    }


}



