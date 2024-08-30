package com.devsu.bank.account_service.application.transaction;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.adapters.dto.TransactionBaseDTO;
import com.devsu.bank.account_service.application.account.AccountService;
import com.devsu.bank.account_service.domain.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.domain.exception.TransactionNotFoundException;
import com.devsu.bank.account_service.domain.model.Account;
import com.devsu.bank.account_service.domain.model.Transaction;
import com.devsu.bank.account_service.domain.model.TransactionType;
import com.devsu.bank.account_service.infraestructure.repository.TransactionRepository;

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
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
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
        Boolean exists = transactionRepository.existsById(id);
        if (!exists) {
            throw new TransactionNotFoundException();
        }
        // TODO: ¿se deberia modificar el saldo de transacciones posteriores?
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction updateById(Long id, TransactionBaseDTO transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
        // TODO: ¿se deberia modificar el saldo de transacciones posteriores?
        transactionToUpdate.setAmount(transaction.getAmount());
        // TODO: ¿se deberia permitir cambiar la cuenta de la transaccion?
        transactionToUpdate.setAccount(new Account(transaction.getAccountId()));
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

    public List<Transaction> findAllByAccountIdAndCreatedAtBetween(Long accountId, Instant startDate,
            Instant endDate) {
        return transactionRepository.findAllByAccountIdAndCreatedAtBetween(
                accountId, startDate, endDate);
    }

}
