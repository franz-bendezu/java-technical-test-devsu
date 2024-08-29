package com.devsu.bank.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @JsonProperty("tipo")
    private String accountType;

    @JsonProperty("montoInicial")
    private Integer initialAmount;

    @JsonProperty("estado")
    private boolean status;

    @JsonProperty("cliente")
    private Long clientId;

}
