package com.devsu.bank.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountDTO extends AccountBaseDTO {

    @JsonProperty("id")
    private Long id;

}
