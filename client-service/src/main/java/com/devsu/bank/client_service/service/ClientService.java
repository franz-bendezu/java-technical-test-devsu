package com.devsu.bank.client_service.service;

import java.util.List;

import com.devsu.bank.client_service.dto.ClientDTO;
import com.devsu.bank.client_service.model.Client;

public interface ClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(ClientDTO client);

    public Client updateById(Long id, ClientDTO client);
    
    public void deleteById(Long id);
}
