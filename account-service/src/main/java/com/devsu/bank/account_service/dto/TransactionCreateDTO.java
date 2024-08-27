package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class TransactionCreateDTO {

    private Integer amount;

    private Long accountId;

    public TransactionCreateDTO() {
    }
}
