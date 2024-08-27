package com.devsu.bank.client_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.bank.client_service.model.Client;
import com.devsu.bank.client_service.repository.ClientRepository;

@Service
public class ClientServiceImpl  implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
