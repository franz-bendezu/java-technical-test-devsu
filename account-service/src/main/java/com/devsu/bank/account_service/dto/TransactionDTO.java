package com.devsu.bank.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionDTO extends TransactionBaseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("saldo")
    private Integer balance;

    @JsonProperty("tipo")
    private String transactionType;

    @JsonProperty("fecha")
    private String createdDate;

}
