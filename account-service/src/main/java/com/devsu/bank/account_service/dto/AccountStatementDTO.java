package com.devsu.bank.account_service.dto;

import java.util.List;

import lombok.Data;


@Data
public class AccountStatementDTO {
    
    private String customerName;

    private List<AccountDTO> accounts;
}
