package com.devsu.bank.account_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String accountType;

    private Integer initialAmount;

    private boolean status;

    private Long clientId;

    @OneToMany(mappedBy = "account")
    private List<Movement> movements;

    public Account() {
    }

    public void setId(Long id) {
        this.id = id;
    }

}
