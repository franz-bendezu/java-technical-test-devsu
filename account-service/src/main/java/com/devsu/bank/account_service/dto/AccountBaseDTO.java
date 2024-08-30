package com.devsu.bank.account_service.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountBaseDTO {

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
    @JsonProperty("clienteId")
    private Long clientId;

}
