package com.devsu.bank.client_service.service;

import java.util.List;

import com.devsu.bank.client_service.model.Client;

public interface ClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(Client client);
    
    public void deleteById(Long id);
}
