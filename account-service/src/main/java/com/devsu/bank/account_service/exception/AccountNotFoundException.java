package com.devsu.bank.account_service.exception;

public class AccountNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Cuenta no encontrada";
    private static final String DEFAULT_CODE = "ACCOUNT_NOT_FOUND";

    private String code = DEFAULT_CODE;

    public AccountNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public AccountNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}