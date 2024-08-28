package com.devsu.bank.account_service.exception;

public class InsufficientBalanceException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Saldo insuficiente";

    public InsufficientBalanceException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}