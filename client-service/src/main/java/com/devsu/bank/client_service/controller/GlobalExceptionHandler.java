package com.devsu.bank.client_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devsu.bank.client_service.dto.ErrorResponse;
import com.devsu.bank.client_service.dto.ErrorValidationResponse;
import com.devsu.bank.client_service.exception.ClientNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(ClientNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),
                        request.getDescription(false), ex.getCode()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidationResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<ErrorValidationResponse.ValidationError> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    ErrorValidationResponse.ValidationError validationError = new ErrorValidationResponse.ValidationError(
                            fieldName, errorMessage);
                    return validationError;
                })
                .collect(Collectors.toList());

        ErrorValidationResponse errorResponse = new ErrorValidationResponse(
                "Campos inválidos", request.getDescription(false), errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}