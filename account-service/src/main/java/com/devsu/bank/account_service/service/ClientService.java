package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.ClientDTO;

public interface ClientService {
    public ClientDTO getClient(Long clientId);
}