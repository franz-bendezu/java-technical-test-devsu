package com.devsu.bank.client_service.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devsu.bank.client_service.dto.ClientDTO;
import com.devsu.bank.client_service.model.Client;
import com.devsu.bank.client_service.repository.ClientRepository;

@Service
public class ClientServiceImpl  implements ClientService {

    private ClientRepository clientRepository;
    
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
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
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        return clientRepository.save(client);
    }

    @Override
    public Client updateById(Long id, ClientDTO client) {
        Client currentClient = clientRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        currentClient.setName(client.getName());
        currentClient.setAddress(client.getAddress());
        currentClient.setPhone(client.getPhone());
        if (client.getPassword() != null) {
            currentClient.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return clientRepository.save(currentClient);
    }


    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

 
}
