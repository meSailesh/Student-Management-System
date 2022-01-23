package com.javaWithSpringBoot.studentmanagementsystem.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sailesh on 1/23/22.
 */@Service
public class StudentCsvParser extends  AbstractCsvParser<StudentDto>{
    @Override
    public Class<? extends StudentDto> getEntity() {
        return StudentDto.class;
    }

    @Override
    public Character getDelimiter() {
        return DelimiterType.COMMA;
    }

    public List<Student> parseAndMapStudents(MultipartFile file) throws Exception{
        if(file.isEmpty()) {
            throw  new Exception("File is Empty");
        }
        List<Student> students = new ArrayList<>();
        List<StudentDto> studentDtos = parse(file);
       for(StudentDto studentDto : studentDtos) {
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           students.add(objectMapper.convertValue(studentDto, Student.class));
       }

       return students;
    }
}
