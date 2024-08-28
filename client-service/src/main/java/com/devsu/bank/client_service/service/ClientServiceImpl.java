package com.devsu.bank.client_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.bank.client_service.dto.ClientDTO;
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
    public Client save(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(clientDTO.getPassword());
        return clientRepository.save(client);
    }

    @Override
    public Client updateById(Long id, ClientDTO client) {
        Client clientToUpdate = clientRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clientToUpdate.setName(client.getName());
        clientToUpdate.setAddress(client.getAddress());
        clientToUpdate.setPhone(client.getPhone());
        clientToUpdate.setPassword(client.getPassword());
        return clientRepository.save(clientToUpdate);
    }


    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

 
}
