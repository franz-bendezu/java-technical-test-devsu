package com.devsu.bank.account_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movementType;

    private Integer amount;

    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    
    public Movement() {
    }

}
