package com.devsu.bank.account_service.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class TransactionDTO {

    private Integer amount;

    private Integer balance;

    private String transactionType;

    private Instant createdAt;
    
}
