package com.javaWithSpringBoot.studentmanagementsystem.exception;

import org.springframework.stereotype.Component;

/**
 * Created by sailesh on 1/16/22.
 */
@Component
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already Exists!");
    }
}
