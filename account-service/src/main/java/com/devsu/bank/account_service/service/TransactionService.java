package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;

import com.devsu.bank.account_service.dto.TransactionBaseDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.model.Transaction;

public interface TransactionService {

    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction create(TransactionBaseDTO transaction);

    public Transaction updateById(Long id, TransactionBaseDTO transaction);

    public void deleteById(Long id);

    public Integer getBalanceByAccount(Account account);

    public List<Transaction> findAllByClientIdAndCreatedAtBetween(Long clientId, Instant startDate, Instant endDate);

    public List<Transaction> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate);
}
