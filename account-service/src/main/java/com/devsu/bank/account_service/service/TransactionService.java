package com.devsu.bank.account_service.service;

import java.util.List;

import com.devsu.bank.account_service.model.Transaction;

public interface TransactionService {

    public List<Transaction> findAll();

    public Transaction findById(Long id);

    public Transaction save(Transaction transaction);

    public void deleteById(Long id);
}
