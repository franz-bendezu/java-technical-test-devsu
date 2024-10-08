package com.devsu.bank.client_service.application;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devsu.bank.client_service.adapters.dto.ClientBaseDTO;
import com.devsu.bank.client_service.adapters.dto.ClientCreateDTO;
import com.devsu.bank.client_service.adapters.dto.ClientUpdateDTO;
import com.devsu.bank.client_service.domain.exception.ClientNotFoundException;
import com.devsu.bank.client_service.domain.model.Client;
import com.devsu.bank.client_service.infraestructure.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

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
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client save(ClientCreateDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setStatus(clientDTO.getStatus());
        client.setGender(clientDTO.getGender());
        client.setAge(clientDTO.getAge());
        client.setIdentification(clientDTO.getIdentification());
        return clientRepository.save(client);
    }

    @Override
    public Client updateById(Long id, ClientUpdateDTO client) {
        Client currentClient = this.findById(id);
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
        Boolean exists = clientRepository.existsById(id);
        if (!exists) {
            throw new ClientNotFoundException();
        }
        // TODO: Comuniarse con el servicio de cuentas para eliminar ( hard delete o soft delete ) las cuentas asociadas al cliente
        clientRepository.deleteById(id);
    }

}
