package com.javaWithSpringBoot.studentmanagementsystem.repository;



import com.javaWithSpringBoot.studentmanagementsystem.repository.custom.StudentRepositoryCustom;
import com.javaWithSpringBoot.studentmanagementsystem.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sailesh on 11/23/21.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Student saveStudentDetails(Student student) {
     entityManager.persist(student);
       return student;
    }

    @Override
    @Transactional
    public Student updateStudentDetails(Student student) {
        entityManager.merge(student);
        return student;
    }

    @Override

    public Student getStudentDetails(Integer studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    public List<Student> getAllStudentDetails() {
        return entityManager.createQuery("Select s from Student s", Student.class).getResultList();
    }

    @Override
    @Transactional
    public Boolean deleteStudent(Student student) {
        entityManager.remove(student);

        return true;

    }
}
