package com.devsu.bank.account_service.dto;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class TransactionCreateDTO {

    @NonNull
    private Integer amount;

    @NonNull
    private Long accountId;

    public TransactionCreateDTO() {
    }
}
