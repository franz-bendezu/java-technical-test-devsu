package com.devsu.bank.client_service.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.devsu.bank.client_service.dto.ClientDataEventDTO;
import com.devsu.bank.client_service.exception.ClientNotFoundException;
import com.devsu.bank.client_service.model.Client;
import com.devsu.bank.client_service.service.ClientService;

@Component
public class ClientConsumer {

    private static final String RESPONSE_TOPIC = "client-info-request";
    private final ClientService clientService;
    private final ClientProducer clientProducer;

    public ClientConsumer(ClientService clientService, ClientProducer clientProducer) {
        this.clientService = clientService;
        this.clientProducer = clientProducer;
    }

    @KafkaListener(topics = RESPONSE_TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void consumeClientRequest(Long clientId) {
        ClientDataEventDTO clientDataEventDTO = new ClientDataEventDTO();
        clientDataEventDTO.setClientId(clientId);
        try {
            Client client = clientService.findById(clientId);
            clientDataEventDTO.setData(client);
            clientProducer.sendClientInfo(clientDataEventDTO);
        } catch (ClientNotFoundException e) {
            clientProducer.sendClientInfo(clientDataEventDTO);
        }
    }
}