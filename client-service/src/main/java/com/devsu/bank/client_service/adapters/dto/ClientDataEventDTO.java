package com.devsu.bank.client_service.adapters.dto;

import com.devsu.bank.client_service.domain.model.Client;

import lombok.Data;

@Data
public class ClientDataEventDTO {
    Client data;
    Long clientId;
}
