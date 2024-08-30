package com.devsu.bank.client_service.model;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {
    private String password;
    private Boolean status;

}
