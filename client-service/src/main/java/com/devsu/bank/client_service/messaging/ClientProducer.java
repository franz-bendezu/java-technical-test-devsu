package com.devsu.bank.client_service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.devsu.bank.client_service.model.Client;

@Component
public class ClientProducer {

    private static final String REQUEST_TOPIC = "client-info-response";

    private KafkaTemplate<String, Client> kafkaTemplate;

    public ClientProducer(KafkaTemplate<String, Client> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClientInfo(Client clientInfo) {
        kafkaTemplate.send(REQUEST_TOPIC, clientInfo);
    }
}