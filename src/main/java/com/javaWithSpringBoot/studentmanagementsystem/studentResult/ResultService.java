package com.javaWithSpringBoot.studentmanagementsystem.studentResult;

import com.javaWithSpringBoot.studentmanagementsystem.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ResultService {
    public final StudentMarkRepository studentMarkRepository;

    @Autowired
    public ResultService(StudentMarkRepository studentMarkRepository){
        this.studentMarkRepository = studentMarkRepository;
    }

    public Result createMarks(Result result){
        return studentMarkRepository.save(result);

    }

    public List<Result> allResult(){
        return studentMarkRepository.findAll();
    }

    public void delteResult (Long resultId)throws Exception{
        Optional<Result> result = studentMarkRepository.findById(resultId);

        if(!result.isPresent()){
            throw new Exception("result not found !!");
        }
        studentMarkRepository.deleteById(resultId);

    }
    public Result updateResult(Long resultId, Result result) throws Exception {
       try {
           Result resultSave = studentMarkRepository.findById(resultId).get();

           if(Objects.nonNull(result.getStudentId()) ){
               resultSave.setStudentId(result.getStudentId());
           }
           if(Objects.nonNull(result.getSubjectId())){
               resultSave.setSubjectId(result.getSubjectId());
           }
           if(Objects.nonNull(result.getSubjectMarks())){
               resultSave.setSubjectMarks(result.getSubjectMarks());
           }
           return studentMarkRepository.save(resultSave);
       }
       catch (Exception e){
           throw new Exception("Some thing went while updating !!");
       }

    }
}
