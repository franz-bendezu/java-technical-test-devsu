package com.devsu.bank.client_service.service;

import java.util.List;

import com.devsu.bank.client_service.dto.ClientCreateDTO;
import com.devsu.bank.client_service.model.Client;

public interface ClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(ClientCreateDTO client);

    public Client updateById(Long id, ClientCreateDTO client);
    
    public void deleteById(Long id);
}
