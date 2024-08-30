package com.devsu.bank.account_service.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsu.bank.account_service.exception.AccountNotFoundException;
import com.devsu.bank.account_service.exception.ClientNotFoundException;
import com.devsu.bank.account_service.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.exception.TransactionNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountNotFoundException> handleYourCustomException(AccountNotFoundException ex) {
        // Directly return a message or status code
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundException> handleRuntimeException(ClientNotFoundException ex) {
        // Directly return a message or status code
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<InsufficientBalanceException> handleInsufficientBalanceException(
            InsufficientBalanceException ex) {
        // Directly return a message or status code
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<TransactionNotFoundException> handleTransactionNotFoundException(
            TransactionNotFoundException ex) {
        // Directly return a message or status code
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}