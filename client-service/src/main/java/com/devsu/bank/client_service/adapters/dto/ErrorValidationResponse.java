package com.devsu.bank.client_service.adapters.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorValidationResponse extends ErrorResponse {
    private List<ValidationError> errors = new ArrayList<>();

    public static class ValidationError {
        private String field;
        private String message;

        public ValidationError() {
        }

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

    }
    public ErrorValidationResponse(String message, String path, List<ValidationError> errors) {
        super(message, path);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}