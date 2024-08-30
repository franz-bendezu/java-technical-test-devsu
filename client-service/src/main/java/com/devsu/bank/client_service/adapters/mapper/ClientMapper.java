package com.devsu.bank.client_service.adapters.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.bank.client_service.adapters.dto.ClientDTO;
import com.devsu.bank.client_service.domain.model.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setStatus(client.getStatus());
        clientDTO.setGender(client.getGender());
        clientDTO.setAge(client.getAge());
        clientDTO.setIdentification(client.getIdentification());
        return clientDTO;
    }

    public static List<ClientDTO> toDTO(List<Client> clients) {
        return clients.stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }
}
