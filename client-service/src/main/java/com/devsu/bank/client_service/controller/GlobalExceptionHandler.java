package com.devsu.bank.client_service.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsu.bank.client_service.exception.ClientNotFoundException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundException> handleRuntimeException(ClientNotFoundException ex) {
        // Directly return a message or status code
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}