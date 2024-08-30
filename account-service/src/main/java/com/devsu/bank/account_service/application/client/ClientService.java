package com.devsu.bank.account_service.application.client;

import com.devsu.bank.account_service.adapters.dto.ClientDTO;

public interface ClientService {
    public ClientDTO findById(Long clientId);
}