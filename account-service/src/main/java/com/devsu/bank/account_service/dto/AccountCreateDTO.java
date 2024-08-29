package com.devsu.bank.account_service.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountCreateDTO {

    @NotNull
    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @NotNull
    @JsonProperty("tipo")
    private String accountType;

    @NotNull
    @Min(0)
    @JsonProperty("montoInicial")
    private Integer initialAmount;

    @NotNull
    @JsonProperty("estado")
    private boolean status;

    @NotNull
    @JsonProperty("cliente")
    private Long clientId;

}
