package com.devsu.bank.account_service.adapters.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReportStatementAccountDTO {

    @JsonProperty("cliente")
    private String customerName;

    @JsonProperty("cuentas")
    private List<StatementAccountDTO> accounts;
}
