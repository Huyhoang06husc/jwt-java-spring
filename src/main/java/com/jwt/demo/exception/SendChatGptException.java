package com.jwt.demo.exception;

public class SendChatGptException extends RuntimeException{

    private String message;

    public SendChatGptException(String message) {
        this.message = message;
    }
}
