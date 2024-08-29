package com.devsu.bank.client_service.exception;

public class ClientNotFoundException  extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Cliente no encontrado";

    public ClientNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}