package com.devsu.bank.client_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientCreateDTO {
    @NotBlank
    @JsonProperty("nombre")
    private String name;

    @NotBlank
    @JsonProperty("direccion")
    private String address;

    @NotBlank
    @JsonProperty("telefono")
    private String phone;

    @NotBlank
    @JsonProperty("contrasena")
    private String password;

    @NotNull
    @JsonProperty("estado")
    private String status;

    @JsonProperty("genero")
    private String gender;

    @JsonProperty("edad")
    private Integer age;

    @JsonProperty("identificacion")
    private String identification;

}
