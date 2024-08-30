package com.devsu.bank.account_service.dto;

public class ErrorResponse {
    private String message;
    private String path;
    private String code;

    public ErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(String message, String path, String code) {
        this.message = message;
        this.path = path;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }


    public String getCode() {
        return code;
    }
}
