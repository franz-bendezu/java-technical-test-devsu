package com.devsu.bank.account_service.dto;

import lombok.Data;

@Data
public class ClientDataEventDTO {
    ClientDTO data;
    Long clientId;
}
