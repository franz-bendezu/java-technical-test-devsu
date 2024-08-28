package com.devsu.bank.account_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionCreateDTO {

    @NotNull
    private Integer amount;

    @NotNull
    private Long accountId;

    public TransactionCreateDTO() {
    }
}
