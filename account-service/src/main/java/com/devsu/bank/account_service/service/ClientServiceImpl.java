package com.devsu.bank.account_service.service;

import java.util.Optional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.ClientDTO;
import com.devsu.bank.account_service.exception.ClientNotFoundException;
import com.devsu.bank.account_service.messaging.ClientConsumer;
import com.devsu.bank.account_service.messaging.ClientProducer;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientProducer clientProducer;
    private final ClientConsumer clientConsumer;

    public ClientServiceImpl(ClientProducer clientProducer, ClientConsumer clientConsumer) {
        this.clientProducer = clientProducer;
        this.clientConsumer = clientConsumer;
    }

    @Override
    public ClientDTO findById(Long clientId) {
        try {

            // Request client info by ID
            clientProducer.requestClientInfo(clientId);
            // Get the future for the client info
            CompletableFuture<Optional<ClientDTO>> clientInfoFuture = clientConsumer.getClientInfoFuture(clientId);
            // Wait for the client info
            Optional<ClientDTO> clientInfo = clientInfoFuture.get(10, TimeUnit.SECONDS);
            return clientInfo.orElseThrow(ClientNotFoundException::new);
        } catch (TimeoutException e) {
            throw new ClientNotFoundException("Se excedió el tiempo de espera para obtener la información del cliente");
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la información del cliente", e);
        }
    }
}