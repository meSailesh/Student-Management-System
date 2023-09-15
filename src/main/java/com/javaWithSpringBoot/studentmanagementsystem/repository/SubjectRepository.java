package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.subject.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    List<Subject> findAll();
}
