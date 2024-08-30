package com.devsu.bank.client_service.adapters.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientBaseDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @JsonProperty("nombre")
    private String name;

    @NotBlank(message = "La direccion no puede estar vacía")
    @JsonProperty("direccion")
    private String address;

    @NotBlank(message = "El correo no puede estar vacío")
    @JsonProperty("telefono")
    private String phone;

    @NotNull(message = "El estado no puede estar vacío")
    @JsonProperty("estado")
    private Boolean status;

    @JsonProperty("genero")
    private String gender;

    @JsonProperty("edad")
    private Integer age;

    @JsonProperty("identificacion")
    private String identification;

}
