package com.devsu.bank.account_service.domain.exception;

public class ClientNotFoundException extends RuntimeException {
    static final String DEFAULT_MESSAGE = "El cliente no fue encontrado";
    static final String DEFAULT_CODE = "CLIENT_NOT_FOUND";

    private String code = DEFAULT_CODE;

    public ClientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ClientNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }
}