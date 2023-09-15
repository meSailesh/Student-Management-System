package com.javaWithSpringBoot.studentmanagementsystem.subject;

import com.javaWithSpringBoot.studentmanagementsystem.repository.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Subject createSubject (Subject subject){
        Boolean isValid = ValidateSubject(subject);
        if(isValid){
            Subject saveSubject = subjectRepository.save(subject);
            return saveSubject;
        }
        return null;

    }

    private Boolean ValidateSubject(Subject subject){
        if(subject == null || subject.getSubjectName().isEmpty()){
            return false;
        }
        return true;
    }

    public List<Subject> getAllSubject (){
        return subjectRepository.findAll();
    }

    public Subject getSubjectDetails (Integer subjectId){
        return subjectRepository.findById(subjectId).get();

    }

    public Subject updateSubjectDetails(Subject subject){
        Boolean isValid = ValidateSubject(subject);
        if(isValid){
            Subject existingSubject = getSubjectDetails(subject.getSubjectId());
            BeanUtils.copyProperties(subject, existingSubject);
            return subjectRepository.save(subject);
        }
        return null;
    }

    public Boolean deleteSubject (Integer subjectId){
        Subject subject = getSubjectDetails(subjectId);
        subjectRepository.delete((subject));
        return true;
    }
}


