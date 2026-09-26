package com.backend.banking_app.exception;

public class InvalidAccountDataException extends RuntimeException {
    public InvalidAccountDataException(String message) {
        super(message);
        System.out.println("InvalidAccountDataException: " + message);
    }
}