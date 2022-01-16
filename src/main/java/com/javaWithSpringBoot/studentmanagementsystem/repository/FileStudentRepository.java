package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import com.javaWithSpringBoot.studentmanagementsystem.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sailesh on 12/15/21.
 */
public class FileStudentRepository
{

    private static final String FILE_NAME = "students.txt";

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

    public Student updateStudentDetails(Student studentToUpdate) {
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
               if(student1.getStudentId().equals(studentToUpdate.getStudentId())) {
                   if(studentToUpdate.getStudentId() != null && !studentToUpdate.getStudentId().equals(student1.getStudentId())) {
                       student1.setStudentId(studentToUpdate.getStudentId());
                   }
                   if(studentToUpdate.getName() != null && !studentToUpdate.getName().equals(student1.getName())) {
                       student1.setName(studentToUpdate.getName());
                   }
                   if(studentToUpdate.getAge() != null && !studentToUpdate.getAge().equals(student1.getAge())) {
                       student1.setAge(studentToUpdate.getAge());
                   }

                   if(studentToUpdate.getAddress() != null && !studentToUpdate.getAddress().equals(student1.getAddress())) {
                       student1.setAddress(studentToUpdate.getAddress());
                   }

                   if(studentToUpdate.getGender() != null && !studentToUpdate.getGender().equals(student1.getGender())) {
                       student1.setGender(studentToUpdate.getGender());
                   }

                   if(studentToUpdate.getDob() != null && !studentToUpdate.getDob().equals(student1.getDob())) {
                       student1.setDob(studentToUpdate.getDob());
                   }
               }
           }
        }

        File studentFile = FileUtils.getFile(FILE_NAME);

        String newStudentRecords = createStudentRecords(students);

        Boolean isSaved = FileUtils.writeDataToFile(studentFile, newStudentRecords);

        if(isSaved) {
            return studentToUpdate;
        }

        return null;
    }


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
        String newStudentRecords = createStudentRecords(students);
        Boolean isSaved = FileUtils.writeDataToFile(studentFile, newStudentRecords);

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
