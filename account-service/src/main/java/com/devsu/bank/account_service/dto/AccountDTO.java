package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class AccountDTO {

    private Long id;

    private String accountNumber;

    private String accountType;

    private Integer initialAmount;

    private boolean status;

    private Long clientId;

}
