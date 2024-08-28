package com.devsu.bank.account_service.exception;

public class TransactionNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Transacción no encontrada";

    public TransactionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}