package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String Password);
}
