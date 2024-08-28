package com.devsu.bank.client_service.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String name;
    private String address;
    private String phone;
    private String password;
    private String status;
}
