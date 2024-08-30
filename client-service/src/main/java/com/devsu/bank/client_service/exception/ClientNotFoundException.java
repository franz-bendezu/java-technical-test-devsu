package com.devsu.bank.client_service.exception;

public class ClientNotFoundException  extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Cliente no encontrado";
    private static final String DEFAULT_CODE = "CLIENT_NOT_FOUND";

    private String code = DEFAULT_CODE;

    public ClientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }
}