package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.TransactionBaseDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.exception.TransactionNotFoundException;
import com.devsu.bank.account_service.mapper.TransactionMapper;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.model.TransactionType;
import com.devsu.bank.account_service.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountService accountService;

    public TransactionServiceImpl(TransactionRepository movementRepository, AccountService accountService) {
        this.transactionRepository = movementRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id).map(TransactionMapper::toDTO)
                .orElseThrow(TransactionNotFoundException::new);
    }
    
    @Override
    public Integer getBalanceByAccount(Account account) {
        Optional<Transaction> lastTransaction = transactionRepository.findLastByAccountId(account.getId());
        return lastTransaction.map(Transaction::getBalance).orElse(account.getInitialAmount());
    }

    @Override
    public Transaction create(TransactionBaseDTO transactionDTO) {
        Account account = this.accountService.findById(transactionDTO.getAccountId());
        Integer balance = getBalanceByAccount(account);
        Integer newBalance = balance + transactionDTO.getAmount();
        if (newBalance < 0) {
            throw new InsufficientBalanceException();
        }

        Transaction transaction = new Transaction();

        if (transactionDTO.getAmount() > 0) {
            transaction.setTransactionType(TransactionType.DEPOSIT);
        } else {
            transaction.setTransactionType(TransactionType.WITHDRAW);
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
    public Transaction updateById(Long id, TransactionBaseDTO transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
        transactionToUpdate.setAmount(transaction.getAmount());
        if (transaction.getAmount() > 0) {
            transactionToUpdate.setTransactionType(TransactionType.DEPOSIT);
        } else {
            transactionToUpdate.setTransactionType(TransactionType.WITHDRAW);
        }
        return transactionRepository.save(transactionToUpdate);
    }
    @Override
    public List<Transaction> findAllByClientIdAndCreatedAtBetween(Long clientId, Instant startDate,
            Instant endDate) {
        return transactionRepository.findAllByClientIdAndCreatedAtBetween(
                clientId, startDate, endDate);
    }

    public List<TransactionDTO> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate) {
        return transactionRepository.findAllByAccountIdAndCreatedAtBetween(
                accountId, startDate, endDate)
                .stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

}
