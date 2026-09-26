package com.backend.banking_app.exception;

public class InSufficientBalanceException extends RuntimeException {

    public InSufficientBalanceException(String message) {
        super(message);
        System.out.println("InSufficientBalanceException: " + message); // Log the exception message
    }
}