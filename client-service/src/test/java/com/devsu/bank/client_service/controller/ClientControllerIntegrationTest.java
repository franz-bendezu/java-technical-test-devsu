package com.devsu.bank.client_service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.devsu.bank.client_service.dto.ClientCreateDTO;
import com.devsu.bank.client_service.model.Client;
import com.devsu.bank.client_service.model.Person;
import com.devsu.bank.client_service.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    Client initialClient;

    @BeforeEach
    void setUp() {
        if (initialClient != null) {
            return;
        }
        Client client = new Client();
        client.setName("John Doe");
        client.setGender("Male");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("123 Main St");
        client.setPhone("555-1234");
        client.setPassword("password");
        client.setStatus(true);

        initialClient = clientRepository.save(client);

    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // Set properties for datasource
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        // Set properties for flyway
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);

    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/clientes/{id}", initialClient.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        ClientCreateDTO client = new ClientCreateDTO();
        client.setName("John Doe");
        client.setGender("Male");
        client.setAge(30);
        client.setIdentification("123456789");
        client.setAddress("123 Main St");
        client.setPhone("555-1234");
        client.setPassword("password");
        client.setStatus(false);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        ClientCreateDTO client = new ClientCreateDTO();
        client.setName("Jane Doe");
        client.setGender("Female");
        client.setAge(25);
        client.setIdentification("987654321");
        client.setAddress("456 Main St");
        client.setPhone("555-5678");
        client.setPassword("password");
        client.setStatus(true);

        mockMvc.perform(put("/clientes/{id}", initialClient.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", initialClient.getId()))
                .andExpect(status().isOk());
    }
}