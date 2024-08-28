package com.devsu.bank.account_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AccountCreateDTO {

    @NotNull
    private String accountNumber;

    @NotNull
    private String accountType;

    @NotNull
    @Min(0)
    private Integer initialAmount;

    @NotNull
    private boolean status;

    @NotNull
    private Long clientId;

}
