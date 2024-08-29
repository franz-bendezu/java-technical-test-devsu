package com.devsu.bank.account_service.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.ClientDTO;
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
        // Request client info by ID
        clientProducer.requestClientInfo(clientId);

        // Get the future for the client info
        CompletableFuture<ClientDTO> clientInfoFuture = clientConsumer.getClientInfoFuture(clientId);

        // Wait for the response with a timeout
        ClientDTO clientInfo;
        try {
            clientInfo = clientInfoFuture.get(10, TimeUnit.SECONDS); // Set a timeout of 10 seconds
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout while waiting for client info", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get client info", e);
        }

        return clientInfo;
    }
}