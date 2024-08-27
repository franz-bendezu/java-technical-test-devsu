package com.devsu.bank.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.model.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    
}
