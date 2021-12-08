package com.javaWithSpringBoot.studentmanagementsystem.repository;



import com.javaWithSpringBoot.studentmanagementsystem.student.Student;
import com.javaWithSpringBoot.studentmanagementsystem.utils.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static final String FILE_NAME = "students.txt";

    @Override
    public Student saveStudentDetails(Student student) {

        if (student != null) {
            File studentFile = FileUtils.getFile(FILE_NAME);
            if (studentFile != null) {
                Integer lastIndex = FileUtils.getLastIndexOfFile(studentFile);
                student.setStudentId(lastIndex + 1);
                String record = student.getStudentId() + "," + student.getName() + "," + student.getAge() + "," + student.getGender() + "," + student.getAddress() + "," + student.getDob();
                Boolean isWritten = FileUtils.appendDateToFile(studentFile, record);
                if (isWritten) {
                    return student;
                } else {
                    System.out.println("Failed to save student details");
                }
            }
        }
        return null;
    }

    @Override
    public Student updateStudentDetails(Student studentFromConsole) {
        //fetching all the records
        //Find the record for the specific student that we want to update
        //finally save all the details

        //delete old file
        //create a new file

        //db operation
        //fetch the record for that specific student
        //update the data
        //save that data

        List<Student> students = getAllStudentDetails();
        if(students != null && !students.isEmpty()) {
           for(Student student1 : students) {
               if(student1.getStudentId().equals(studentFromConsole.getStudentId())) {
                   if(studentFromConsole.getStudentId() != null && !studentFromConsole.getStudentId().equals(student1.getStudentId())) {
                       student1.setStudentId(studentFromConsole.getStudentId());
                   }
                   if(studentFromConsole.getName() != null && !studentFromConsole.getName().equals(student1.getName())) {
                       student1.setName(studentFromConsole.getName());
                   }
                   if(studentFromConsole.getAge() != null && !studentFromConsole.getAge().equals(student1.getAge())) {
                       student1.setAge(studentFromConsole.getAge());
                   }


               }
           }
        }

        File studentFile = FileUtils.getFile(FILE_NAME);
        if(studentFile.delete()) {

        } else {
            System.out.println("Could not delete file");
        }

        File newFile = FileUtils.getFile(FILE_NAME);
        String newStudentRecords = createStudentRecords(students);

        Boolean isSaved = FileUtils.writeDataToFile(newFile, newStudentRecords);

        if(isSaved) {
            return studentFromConsole;
        }

        return null;
    }

    @Override
    public Student getStudentDetails(Integer studentId) {

        File studentFile = FileUtils.getFile(FILE_NAME);
        if (studentFile != null) {
            String recordString = FileUtils.readDataFromFileBasedOnIndex(studentFile, studentId);
            if (recordString != null && !recordString.isEmpty()) {
                return createStudentObject(recordString);
            }
        }
        return null;
    }

    @Override
    public List<Student> getAllStudentDetails() {
        //read file
        //map records to student object
        //return student object
        File studentFile = FileUtils.getFile(FILE_NAME);
        if (studentFile != null) {
            String recordString = FileUtils.readDataFromFile(studentFile);
            if (recordString != null && !recordString.isEmpty()) {
                String[] records = recordString.split("\n");

                List<Student> students = new ArrayList<>();
                for (String record : records) {
                    if (record != null && !record.isEmpty()) {
                        Student student = createStudentObject(record);
                        students.add(student);

                    }
                }
                return students;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteStudent(Integer studentId) {

        List<Student> students = getAllStudentDetails();

        Integer indexToRemove = null;
        if(students != null && !students.isEmpty()) {
            for(Student student1 : students) {
                if(student1.getStudentId().equals(studentId)) {
                    indexToRemove = students.indexOf(student1);
                }
            }
        }

       if(indexToRemove != null ){
           students.remove(indexToRemove.intValue());
       }

        File studentFile = FileUtils.getFile(FILE_NAME);
        if(studentFile.delete()) {

        } else {
            System.out.println("Could not delete file");
        }

        File newFile = FileUtils.getFile(FILE_NAME);
        String newStudentRecords = createStudentRecords(students);

        Boolean isSaved = FileUtils.writeDataToFile(newFile, newStudentRecords);

        if(isSaved) {
            return true;
        }

        return false;
    }

    private Student createStudentObject(String record) {
        String[] fields = record.split(",");
        Student student = new Student();
        student.setStudentId(Integer.parseInt(fields[0]));
        student.setName(fields[1].trim());
        student.setAge(Integer.parseInt(fields[2].trim()));
        student.setGender(fields[3].trim());
        student.setAddress(fields[4].trim());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            student.setDob(sdf.parse(fields[5]));
        } catch (ParseException e) {
            System.out.println("Error parsing the dob");
            e.printStackTrace();
        }

        return student;
    }

    private String createStudentRecords(List<Student> students) {
        if(students != null && !students.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for(Student student : students) {
                stringBuilder.append(student.toCsvString());
            }

            return  stringBuilder.toString();
        }
        return null;
    }
}

//
//DTO
//DAO/Entity
//
//
//DAO -> datbase -> this directly maps to the database or any other source
//
//DTO -> transfer data from one layer to another mostly from service layer  to view layer/ controller layer
//
//transfer data using paramater
//
//20 different fields that you want to send as a parameter

