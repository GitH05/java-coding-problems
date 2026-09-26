package com.backend.banking_app.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAccountNotFoundException(
            AccountNotFoundException ex) {

        return new ResponseEntity<>(
                Map.of("message", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InSufficientBalanceException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientBalanceException(
            InSufficientBalanceException ex) {

        return new ResponseEntity<>(
                Map.of("message", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccountDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAccountDataException(
            InvalidAccountDataException ex) {
        return new ResponseEntity<>(
                Map.of("message", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {

        return new ResponseEntity<>(
                Map.of("message", "Something went wrong: " + ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}