package com.jwt.demo.exception;

public class SendMailException extends RuntimeException{

    private String message;

    public SendMailException(String message) {
        super(message);
        this.message = message;
    }
}
