package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;

import com.devsu.bank.account_service.dto.TransactionCreateDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.model.Transaction;

public interface TransactionService {

    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction create(TransactionCreateDTO transaction);

    public Transaction updateById(Long id, TransactionCreateDTO transaction);

    public void deleteById(Long id);

    public List<TransactionDTO> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate);
}
