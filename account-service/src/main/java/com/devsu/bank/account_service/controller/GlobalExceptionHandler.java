package com.devsu.bank.account_service.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devsu.bank.account_service.dto.ErrorResponse;
import com.devsu.bank.account_service.exception.AccountNotFoundException;
import com.devsu.bank.account_service.exception.ClientNotFoundException;
import com.devsu.bank.account_service.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.exception.TransactionNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex,
			WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), request.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(ClientNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(
				new ErrorResponse(ex.getMessage(), request.getDescription(false), ex.getCode()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(
			InsufficientBalanceException ex, WebRequest request) {
		return new ResponseEntity<>(
				new ErrorResponse(ex.getMessage(), request.getDescription(false), ex.getCode()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTransactionNotFoundException(
			TransactionNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(
				new ErrorResponse(ex.getMessage(), request.getDescription(false), ex.getCode()),
				HttpStatus.NOT_FOUND);
	}

}