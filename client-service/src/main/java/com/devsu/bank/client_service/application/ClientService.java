package com.devsu.bank.client_service.application;

import java.util.List;

import com.devsu.bank.client_service.adapters.dto.ClientBaseDTO;
import com.devsu.bank.client_service.adapters.dto.ClientCreateDTO;
import com.devsu.bank.client_service.adapters.dto.ClientUpdateDTO;
import com.devsu.bank.client_service.domain.model.Client;

public interface ClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(ClientCreateDTO client);

    public Client updateById(Long id, ClientUpdateDTO client);
    
    public void deleteById(Long id);
}
