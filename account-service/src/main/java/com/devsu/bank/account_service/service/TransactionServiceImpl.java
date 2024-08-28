package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.config.CommonSettings;
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
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id).map(this::convertToTransactionDTO)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
    }

    @Override
    public TransactionDTO create(TransactionCreateDTO transactionDTO) {
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

        Transaction transactionResult = transactionRepository.save(transaction);
        return convertToTransactionDTO(transactionResult);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public TransactionDTO updateById(Long id, TransactionCreateDTO transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
        transactionToUpdate.setAmount(transaction.getAmount());
        if (transaction.getAmount() > 0) {
            transactionToUpdate.setTransactionType("DEPOSIT");
        } else {
            transactionToUpdate.setTransactionType("WITHDRAW");
        }
        Transaction transactionResult = transactionRepository.save(transactionToUpdate);
        return convertToTransactionDTO(transactionResult);
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
        transactionDTO
                .setCreatedDate(transaction.getCreatedAt().atZone(CommonSettings.TIME_ZONE).toLocalDate().toString());
        transactionDTO.setAccountId(transaction.getAccount().getId());
        transactionDTO.setId(transaction.getId());
        return transactionDTO;
    }

}
