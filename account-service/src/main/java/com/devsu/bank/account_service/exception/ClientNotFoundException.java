package com.devsu.bank.account_service.exception;

public class ClientNotFoundException extends RuntimeException {
    static final String DEFAULT_MESSAGE = "El cliente no fue encontrado";

    public ClientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}