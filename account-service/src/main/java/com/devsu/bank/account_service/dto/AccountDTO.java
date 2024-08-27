package com.devsu.bank.account_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class AccountDTO {

    private String accountNumber;

    private Integer initialAmount;

    private List<TransactionDTO> transactions;

}
