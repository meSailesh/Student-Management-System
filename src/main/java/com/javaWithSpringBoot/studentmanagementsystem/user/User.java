package com.javaWithSpringBoot.studentmanagementsystem.user;

/**
 * Created by sailesh on 12/6/21.
 */
public class User {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}