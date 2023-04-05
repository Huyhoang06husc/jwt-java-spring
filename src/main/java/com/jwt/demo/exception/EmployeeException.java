package com.jwt.demo.exception;

public class EmployeeException extends RuntimeException{

    private String message;

    public EmployeeException(String message) {
        this.message = message;
    }
}
