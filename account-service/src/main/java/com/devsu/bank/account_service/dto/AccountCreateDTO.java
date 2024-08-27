package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class AccountCreateDTO {

    private String accountNumber;

    private String accountType;

    private Integer initialAmount;

    private boolean status;

    private Long clientId;

}
