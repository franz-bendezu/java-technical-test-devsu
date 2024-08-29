package com.devsu.bank.account_service.messaging;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.ClientDTO;
import com.devsu.bank.account_service.dto.ClientDataEventDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ClientConsumer {

    private static final String RESPONSE_TOPIC = "client-info-response";
    private final ConcurrentMap<Long, CompletableFuture<Optional<ClientDTO>>> futures = new ConcurrentHashMap<>();

    @KafkaListener(topics = RESPONSE_TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void consume(ClientDataEventDTO clientDTO) {
        CompletableFuture<Optional<ClientDTO>> future = futures.remove(clientDTO.getClientId());
        if (future != null) {
            future.complete(Optional.ofNullable(clientDTO.getData()));
        }
    }

    public CompletableFuture<Optional<ClientDTO>> getClientInfoFuture(Long clientId) {
        CompletableFuture<Optional<ClientDTO>> future = new CompletableFuture<>();
        futures.put(clientId, future);
        return future;
    }
}