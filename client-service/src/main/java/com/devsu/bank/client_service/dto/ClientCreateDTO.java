package com.devsu.bank.client_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientCreateDTO extends ClientBaseDTO {

    @NotNull(message = "La contrasena no puede estar vacía")
    @NotBlank(message = "La contrasena no puede estar vacía")
    @JsonProperty("contrasena")
    private String password;

}