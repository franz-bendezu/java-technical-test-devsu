package com.devsu.bank.client_service.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person {
    private String password;
    private String status;
    
}
