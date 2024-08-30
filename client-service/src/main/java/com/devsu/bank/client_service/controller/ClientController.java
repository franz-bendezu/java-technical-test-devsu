package com.devsu.bank.client_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.client_service.dto.ClientBaseDTO;
import com.devsu.bank.client_service.dto.ClientDTO;
import com.devsu.bank.client_service.mapper.ClientMapper;
import com.devsu.bank.client_service.service.ClientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping(ClientController.PATH)
public class ClientController {
    public static final String PATH = "/clientes";

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> findAll() {
        return ClientMapper.toDTO(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return ClientMapper.toDTO(clientService.findById(id));
    }

    @PostMapping
    public ClientDTO save(@Valid @RequestBody ClientBaseDTO client) {
        return ClientMapper.toDTO(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id, @Valid @RequestBody ClientBaseDTO client) {
        return ClientMapper.toDTO(clientService.updateById(id, client));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

}
