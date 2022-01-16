package com.javaWithSpringBoot.studentmanagementsystem.repository;

import com.javaWithSpringBoot.studentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sailesh on 1/16/22.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
