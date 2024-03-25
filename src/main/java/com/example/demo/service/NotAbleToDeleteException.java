package com.example.demo.service;

public class NotAbleToDeleteException extends RuntimeException {
    public NotAbleToDeleteException() {
        super();
    }

    public NotAbleToDeleteException(String message) {
        super(message);
    }
}
