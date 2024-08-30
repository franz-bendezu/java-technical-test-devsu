package com.devsu.bank.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionBaseDTO {

    @NotNull
    @JsonProperty("valor")
    private Integer amount;

    @NotNull
    @JsonProperty("cuenta")
    private Long accountId;

}
