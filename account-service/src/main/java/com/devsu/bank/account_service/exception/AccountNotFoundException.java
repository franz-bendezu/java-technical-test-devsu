package com.devsu.bank.account_service.exception;


public class AccountNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Cuenta no encontrada";

    public AccountNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    
    public AccountNotFoundException(String message) {
        super(message);
    }
}