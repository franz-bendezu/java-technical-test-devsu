package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class ClientDataEventDTO {
    private ClientDTO data;
    private Long clientId;
}
