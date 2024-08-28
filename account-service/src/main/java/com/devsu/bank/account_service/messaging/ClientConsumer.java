package com.devsu.bank.account_service.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.ClientDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ClientConsumer {

    private static final String RESPONSE_TOPIC = "client-info-response";
    private final ConcurrentMap<Long, CompletableFuture<ClientDTO>> futures = new ConcurrentHashMap<>();

    @KafkaListener(topics = RESPONSE_TOPIC)
    public void consume(ClientDTO clientDTO) {
        CompletableFuture<ClientDTO> future = futures.remove(clientDTO.getId());
        if (future != null) {
            future.complete(clientDTO);
        }
    }

    public CompletableFuture<ClientDTO> getClientInfoFuture(Long clientId) {
        CompletableFuture<ClientDTO> future = new CompletableFuture<>();
        futures.put(clientId, future);
        return future;
    }
}