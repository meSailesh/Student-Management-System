package com.javaWithSpringBoot.studentmanagementsystem.repository;



import com.javaWithSpringBoot.studentmanagementsystem.utils.FileUtils;
import com.javaWithSpringBoot.studentmanagementsystem.studentMarks.StudentMark;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sailesh on 11/26/21.
 */
public class StudentMarkRepositoryImpl implements StudentMarkRepository
{

    private static final String FILE_NAME = "studentMarks.txt";
    @Override
    public List<StudentMark> createStudentMark(List<StudentMark> studentMarkList) {

        //1,1,1,85 \n 2,1,2,86 \n 3,1,3,45
        File studentMarkFile = FileUtils.getFile(FILE_NAME);
        Integer lastIndex = FileUtils.getLastIndexOfFile(studentMarkFile);
        String studentMarkRecord = createCsvRecord(studentMarkList, lastIndex);
        if(studentMarkFile != null) {
            Boolean isWritten = FileUtils.appendDateToFile(studentMarkFile, studentMarkRecord);
            if(isWritten){
                return studentMarkList;
            }

        }
        return null;
    }

    @Override
    public List<StudentMark> getStudentMark(Integer studentId) {
        File markFile = FileUtils.getFile(FILE_NAME);
        if (markFile != null) {
            String recordString = FileUtils.readDateFromFileBasedOnIndexAndValue(markFile, 1,  studentId);
            List<StudentMark> studentMarks = toList(recordString);
            return studentMarks;

        }
        return null;
    }


    private String createCsvRecord(List<StudentMark> studentMarkList, Integer lastIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer length = studentMarkList.size();

        Integer index = lastIndex +1;
        for(StudentMark studentMark : studentMarkList) {
            studentMark.setStudentMarkId(index);
            index = index + 1;
            stringBuilder.append(studentMark.toCsvString());
            if(studentMarkList.indexOf(studentMark) != (length-1)) {
                stringBuilder.append("\n");
            }
         }

        return stringBuilder.toString();
    }

    private List<StudentMark> toList(String recordStr) {
        List<StudentMark> studentMarkList = new ArrayList<>();
        if(recordStr != null && !recordStr.isEmpty()) {
            String[] records = recordStr.split("\n");
            for(String record : records) {
                String[] fields = record.split(",");
                StudentMark studentMark = new StudentMark();
                studentMark.setStudentMarkId(Integer.parseInt(fields[0]));
                studentMark.setStudentId(Integer.parseInt(fields[1]));
                studentMark.setSubjectId(Integer.parseInt(fields[2]));
                studentMark.setMarks(Double.parseDouble(fields[3]));

                studentMarkList.add(studentMark);
            }

        }
        return studentMarkList;
    }




}
