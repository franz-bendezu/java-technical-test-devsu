package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class TransactionDTO {

    private Integer amount;

    private Long accountId;

    public TransactionDTO() {
    }
}
