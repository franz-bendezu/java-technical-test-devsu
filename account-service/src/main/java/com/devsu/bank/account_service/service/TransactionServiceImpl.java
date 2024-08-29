package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.TransactionCreateDTO;
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
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id).map(TransactionMapper::toDTO)
                .orElseThrow(TransactionNotFoundException::new);
    }

    @Override
    public Transaction create(TransactionCreateDTO transactionDTO) {
        Account account = this.accountService.findById(transactionDTO.getAccountId());
        Optional<Transaction> lastTransaction = transactionRepository
                .findLastByAccountId(transactionDTO.getAccountId());
        Integer balance = lastTransaction.map(Transaction::getBalance).orElse(account.getInitialAmount());
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
    public Transaction updateById(Long id, TransactionCreateDTO transaction) {
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

    public List<TransactionDTO> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate) {
        return transactionRepository.findAllByAccountIdAndCreatedAtBetween(
                accountId, startDate, endDate)
                .stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

}
