package com.backend.banking_app.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
        System.out.println("AccountNotFoundException: " + message);
    }
}