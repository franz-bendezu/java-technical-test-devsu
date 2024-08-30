package com.devsu.bank.account_service.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClientProducer {

    private static final String REQUEST_TOPIC = "client-info-request";

    private final KafkaTemplate<String, Long> kafkaTemplate;

    public ClientProducer(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void requestClientInfo(Long clientId) {
        kafkaTemplate.send(REQUEST_TOPIC, clientId);
    }
}