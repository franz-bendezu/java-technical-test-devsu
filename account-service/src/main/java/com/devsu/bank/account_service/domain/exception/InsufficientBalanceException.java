package com.devsu.bank.account_service.domain.exception;

public class InsufficientBalanceException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Saldo insuficiente";

    private static final String DEFAULT_CODE = "INSUFFICIENT_BALANCE";
    private String code = DEFAULT_CODE;

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalanceException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public InsufficientBalanceException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }


    public String getCode() {
        return code;
    }
    
}