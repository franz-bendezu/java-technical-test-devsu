package com.devsu.bank.client_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotNull
    private String status;
}
