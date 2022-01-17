package com.javaWithSpringBoot.studentmanagementsystem.exception;

import org.springframework.stereotype.Component;

/**
 * Created by sailesh on 1/16/22.
 */
@Component
public class PasswordMissMatchException extends RuntimeException{
    public PasswordMissMatchException() {
        super("Password Miss Match.");
    }
}
