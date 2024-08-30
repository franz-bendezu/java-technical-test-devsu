package com.devsu.bank.account_service.infraestructure.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.domain.model.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId ORDER BY t.id DESC LIMIT 1")
    Optional<Transaction> findLastByAccountId(Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Transaction> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate, Instant endDate);

    @Query("SELECT t FROM Transaction t JOIN Account a ON t.account.id = a.id WHERE a.clientId = :clientId AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Transaction> findAllByClientIdAndCreatedAtBetween(Long clientId, Instant startDate, Instant endDate);

    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.account.id = :accountId")
    void deleteAllByAccountId(Long accountId);
}
