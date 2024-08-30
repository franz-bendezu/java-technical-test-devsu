package com.devsu.bank.account_service.exception;

public class TransactionNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Transacción no encontrada";
    private static final String CODE = "TRANSACTION_NOT_FOUND";

    private String code = CODE;

    public TransactionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public TransactionNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}