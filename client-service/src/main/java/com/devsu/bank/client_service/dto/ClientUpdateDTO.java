package com.devsu.bank.client_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientUpdateDTO extends ClientBaseDTO {

    @JsonProperty("contrasena")
    private String password;

}