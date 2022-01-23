package com.javaWithSpringBoot.studentmanagementsystem.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.model.Response;
import com.javaWithSpringBoot.studentmanagementsystem.parser.DelimiterType;
import com.javaWithSpringBoot.studentmanagementsystem.parser.StudentCsvParser;
import com.javaWithSpringBoot.studentmanagementsystem.parser.StudentDto;
import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentRepository;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sailesh on 11/23/21.
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentCsvParser studentCsvParser;

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


    public void saveStudentsFromFile(MultipartFile file) throws Exception{
        Response response = new Response();

            studentRepository.saveAll(studentCsvParser.parseAndMapStudents(file));
            studentRepository.flush();
    }

    public void exportDummyStudentFile(HttpServletResponse response) throws  Exception {
        Student student = new Student();
        student.setName("John Doe");
        student.setAddress("New York");
        student.setAge(45);
        student.setGender("Male");
        student.setDob(new Date());
        prepareFileDownloadResponse(Collections.singletonList(student), response);

    }

    public void exportStudentFile(HttpServletResponse response) throws  Exception {
       List<Student> students = getAllStudents();
        prepareFileDownloadResponse(students, response);

    }

    private void prepareFileDownloadResponse(List<Student> students, HttpServletResponse response) throws Exception {
        String fileName = "students"+ DateUtils.format(new Date(), "yyyy-MM-dd", Locale.ENGLISH)+".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"" + fileName +"\"");

        //create a csv writer
        StatefulBeanToCsv<Student> writer = new StatefulBeanToCsvBuilder<Student>(response.getWriter())
                .withSeparator(DelimiterType.PIPE)
                .withIgnoreField(Student.class, Student.class.getDeclaredField("studentId"))
                .withOrderedResults(false)
                .build();

        //write student to csv file
        writer.write(students);
    }


}



