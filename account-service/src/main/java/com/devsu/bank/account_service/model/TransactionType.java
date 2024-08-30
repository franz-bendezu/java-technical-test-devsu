package com.devsu.bank.account_service.model;

// assign the TransactionType enum to the TransactionType attribute name
public enum TransactionType {
    DEPOSIT("Deposito"), WITHDRAW("Retiro");

    private String name;

    public String getName() {
        return name;
    }

    TransactionType(String name) {
        this.name = name;
    }
}
