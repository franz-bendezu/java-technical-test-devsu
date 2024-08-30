package com.devsu.bank.account_service.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountBaseDTO {

    @NotNull(message = "El número de cuenta es requerido")
    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @NotNull(message = "El tipo de cuenta es requerido")
    @JsonProperty("tipo")
    private String accountType;

    @NotNull(message = "El monto inicial es requerido")
    @Min(value = 0, message = "El monto inicial debe ser mayor o igual a 0")
    @JsonProperty("montoInicial")
    private Integer initialAmount;

    @NotNull(message = "El estado de la cuenta es requerido")
    @JsonProperty("estado")
    private boolean status;

    @NotNull(message = "El id del cliente es requerido")
    @JsonProperty("clienteId")
    private Long clientId;

}
