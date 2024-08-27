package com.devsu.bank.account_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.model.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId ORDER BY t.id DESC LIMIT 1")
    Optional<Transaction> findLastTransactionByAccountId(Long accountId);
}
