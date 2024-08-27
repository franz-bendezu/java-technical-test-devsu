package com.devsu.bank.account_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository movementRepository) {
        this.transactionRepository = movementRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
    
}
