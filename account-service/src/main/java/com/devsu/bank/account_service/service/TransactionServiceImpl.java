package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.TransactionCreateDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.exception.AccountNotFoundException;
import com.devsu.bank.account_service.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.exception.TransactionNotFoundException;
import com.devsu.bank.account_service.mapper.TransactionMapper;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.model.Transaction;
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
    public TransactionDTO create(TransactionCreateDTO transactionDTO) {
        Account account = this.accountService.findById(transactionDTO.getAccountId());
        Optional<Transaction> lastTransaction = transactionRepository
                .findLastTransactionByAccountId(transactionDTO.getAccountId());
        Integer balance = lastTransaction.map(Transaction::getBalance).orElse(0);
        Integer newBalance = balance + transactionDTO.getAmount();
        if (newBalance < 0) {
            throw new InsufficientBalanceException();
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

        Transaction transactionResult = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(transactionResult);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public TransactionDTO updateById(Long id, TransactionCreateDTO transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
        transactionToUpdate.setAmount(transaction.getAmount());
        if (transaction.getAmount() > 0) {
            transactionToUpdate.setTransactionType("DEPOSIT");
        } else {
            transactionToUpdate.setTransactionType("WITHDRAW");
        }
        Transaction transactionResult = transactionRepository.save(transactionToUpdate);
        return TransactionMapper.toDTO(transactionResult);
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
