package com.devsu.bank.client_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.devsu.bank.client_service.exception.ClientNotFoundException;
import com.devsu.bank.client_service.model.Client;
import com.devsu.bank.client_service.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setAddress("123 Main St");
        client.setPhone("987654321");
        client.setIdentification("123456789");
        client.setGender("M");
        client.setAge(30);
    }

    @Test
    public void testFindClientById() throws ClientNotFoundException {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client foundClient = clientService.findById(1L);

        assertNotNull(foundClient);
        assertEquals(1, foundClient.getId());
        assertEquals("John Doe", foundClient.getName());
        assertEquals("123 Main St", foundClient.getAddress());
        assertEquals("987654321", foundClient.getPhone());
        assertEquals("123456789", foundClient.getIdentification());
    }

    @Test()
    public void testFindClientByIdNotFound() throws ClientNotFoundException {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ClientNotFoundException.class, () -> {

            clientService.findById(1L);
        });
    }
}