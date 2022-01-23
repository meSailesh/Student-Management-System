package com.javaWithSpringBoot.studentmanagementsystem.model;

/**
 * Created by sailesh on 1/23/22.
 */
public class Response {
    private String message;
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
