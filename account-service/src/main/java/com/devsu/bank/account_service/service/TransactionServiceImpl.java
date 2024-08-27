package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.TransactionCreateDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.repository.AccountRepository;
import com.devsu.bank.account_service.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository movementRepository, AccountRepository accountRepository) {
        this.transactionRepository = movementRepository;
        this.accountRepository = accountRepository;
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
    public Transaction create(TransactionCreateDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        Optional<Transaction> lastTransaction = transactionRepository
                .findLastTransactionByAccountId(transactionDTO.getAccountId());
        Integer balance = lastTransaction.map(Transaction::getBalance).orElse(0);
        Integer newBalance = balance + transactionDTO.getAmount();
        if (newBalance < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        if (transactionDTO.getAmount() == 0) {
            throw new RuntimeException("El monto de la transacción debe ser diferente de 0");
        }

        Transaction transaction = new Transaction();

        if (transactionDTO.getAmount() > 0) {
            transaction.setTransactionType("DEPOSIT");
        } else {
            transaction.setTransactionType("WITHDRAW");
        }
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setAccount(account);
        transaction.setBalance(newBalance);

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction updateById(Long id, TransactionCreateDTO transaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    public List<TransactionDTO> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate) {
        return transactionRepository.findAllByAccountIdAndCreatedAtBetween(
                accountId, startDate, endDate)
                .stream()
                .map(this::convertToTransactionDTO)
                .collect(Collectors.toList());
    }

    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setBalance(transaction.getBalance());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setCreatedAt(transaction.getCreatedAt());
        return transactionDTO;
    }

}
