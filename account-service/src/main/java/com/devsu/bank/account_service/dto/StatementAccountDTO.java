package com.devsu.bank.account_service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatementAccountDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("numeroCuenta")
    private String accountNumber;

    @JsonProperty("saldoActual")
    private Integer currentAmount;

    @JsonProperty("movimientos")
    private List<TransactionDTO> transactions;

}
