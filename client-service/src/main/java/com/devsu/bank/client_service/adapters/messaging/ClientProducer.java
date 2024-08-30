package com.devsu.bank.client_service.adapters.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.devsu.bank.client_service.adapters.dto.ClientDataEventDTO;

@Component
public class ClientProducer {

    private static final String REQUEST_TOPIC = "client-info-response";

    private KafkaTemplate<String, ClientDataEventDTO> kafkaTemplate;

    public ClientProducer(KafkaTemplate<String, ClientDataEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClientInfo(ClientDataEventDTO clientDataEventDTO) {
        kafkaTemplate.send(REQUEST_TOPIC, clientDataEventDTO);
    }
}