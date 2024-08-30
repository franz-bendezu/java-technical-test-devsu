package com.devsu.bank.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionBaseDTO {

    @NotNull(message = "El valor de la transacción es requerido")
    @JsonProperty("valor")
    private Integer amount;

    @NotNull(message = "El id de la cuenta es requerido")
    @JsonProperty("cuentaId")
    private Long accountId;

}
