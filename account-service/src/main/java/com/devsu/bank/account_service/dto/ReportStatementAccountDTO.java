package com.devsu.bank.account_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReportStatementAccountDTO {

    private String customerName;

    private List<StatementAccountDTO> accounts;
}
