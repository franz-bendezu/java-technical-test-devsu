package com.devsu.bank.client_service.dto;

import com.devsu.bank.client_service.model.Client;

import lombok.Data;

@Data
public class ClientDataEventDTO {
    Client data;
    Long clientId;
}
