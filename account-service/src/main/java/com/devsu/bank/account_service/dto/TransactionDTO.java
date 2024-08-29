package com.devsu.bank.account_service.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("valor")
    private Integer amount;

    @JsonProperty("saldo")
    private Integer balance;

    @JsonProperty("tipo")
    private String transactionType;

    @JsonProperty("fecha")
    private String createdDate;
    
    @JsonProperty("cuenta")
    private Long accountId;
}
