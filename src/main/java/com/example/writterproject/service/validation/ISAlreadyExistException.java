package com.example.writterproject.service.validation;

public class ISAlreadyExistException extends RuntimeException {
    public ISAlreadyExistException(String message) {
        super(message);
    }
}
