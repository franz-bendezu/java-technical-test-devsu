package com.devsu.bank.client_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    @NotNull
    private String status;
}
