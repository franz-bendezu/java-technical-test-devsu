package com.devsu.bank.account_service.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class TransactionDTO {

    private Long id;

    private Integer amount;

    private Integer balance;

    private String transactionType;

    private String createdDate;
    
    private Long accountId;
}
