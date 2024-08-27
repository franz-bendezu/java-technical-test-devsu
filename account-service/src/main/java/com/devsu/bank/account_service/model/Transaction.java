package com.devsu.bank.account_service.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;

    private Integer amount;

    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @CreatedDate
    private Instant createdAt;

    public Transaction() {
    }
}
