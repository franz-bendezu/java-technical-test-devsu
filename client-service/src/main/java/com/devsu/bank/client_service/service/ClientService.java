package com.devsu.bank.client_service.service;

import java.util.List;

import com.devsu.bank.client_service.dto.ClientBaseDTO;
import com.devsu.bank.client_service.model.Client;

public interface ClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(ClientBaseDTO client);

    public Client updateById(Long id, ClientBaseDTO client);
    
    public void deleteById(Long id);
}
