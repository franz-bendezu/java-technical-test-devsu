package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.TransactionBaseDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.exception.InsufficientBalanceException;
import com.devsu.bank.account_service.exception.TransactionNotFoundException;
import com.devsu.bank.account_service.mapper.TransactionMapper;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.model.TransactionType;
import com.devsu.bank.account_service.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Account createAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setClientId(1L);
        account.setInitialAmount(500);
        return account;
    }

    private Transaction createTransaction(Long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        Account account = createAccount(1L);
        transaction.setAccount(account);
        transaction.setAmount(100);
        transaction.setBalance(100);
        transaction.setCreatedAt(Instant.now());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return transaction;
    }

    @Test
    public void shouldReturnAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createTransaction(1L));
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.findAll();

        Assertions.assertEquals(transactions.size(), result.size());
    }

    @Test
    public void shouldReturnTransactionById() {
        Long id = 1L;
        Transaction transaction = createTransaction(id);
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findById(id);

        Assertions.assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenTransactionNotFoundById() {
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.findById(id);
        });
    }

    @Test
    public void shouldCreateTransaction() {
        Long accountId = 1L;
        Integer amount = 100;
        TransactionBaseDTO transactionDTO = new TransactionBaseDTO();
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(amount);

        Account account = new Account();
        account.setInitialAmount(500);
        account.setId(accountId);
        account.setClientId(1L);
        when(accountService.findById(accountId)).thenReturn(account);

        Transaction lastTransaction = new Transaction();
        lastTransaction.setBalance(400);
        lastTransaction.setAccount(account);
        Optional<Transaction> optionalLastTransaction = Optional.of(lastTransaction);
        when(transactionRepository.findLastByAccountId(accountId)).thenReturn(optionalLastTransaction);

        Transaction savedTransaction = createTransaction(1L);
        savedTransaction.setBalance(500);
        savedTransaction.setCreatedAt(Instant.now());

        when(transactionRepository.save(
                argThat(transaction -> transaction.getAmount().equals(amount)
                        && transaction.getBalance().equals(savedTransaction.getBalance()))))
                .thenReturn(savedTransaction);

        Transaction result = transactionService.create(transactionDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(amount, result.getAmount());
        Assertions.assertEquals(TransactionType.DEPOSIT,
                result.getTransactionType());
        Assertions.assertEquals(account.getClientId(), result.getAccount().getClientId());
        Assertions.assertEquals(500, result.getBalance());
    }

    @Test
    public void shouldThrowExceptionWhenInsufficientBalance() {
        Long accountId = 1L;
        Integer amount = -100;
        TransactionBaseDTO transactionDTO = new TransactionBaseDTO();
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(amount);

        Account account = new Account();
        account.setInitialAmount(50);
        when(accountService.findById(accountId)).thenReturn(account);

        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.create(transactionDTO);
        });
    }

    @Test
    public void shouldDeleteTransactionById() {
        Long id = 1L;

        transactionService.deleteById(id);

        verify(transactionRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdateTransactionById() {
        Long id = 1L;
        Integer amount = 200;
        TransactionBaseDTO transactionDTO = new TransactionBaseDTO();
        transactionDTO.setAmount(amount);

        Transaction existingTransaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(existingTransaction));

        Transaction updatedTransaction = createTransaction(id);
        updatedTransaction.setAmount(amount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(updatedTransaction);

        Transaction result = transactionService.updateById(id, transactionDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(amount, result.getAmount());
        Assertions.assertEquals(TransactionType.DEPOSIT, result.getTransactionType());
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistentTransaction() {
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.updateById(id, new TransactionBaseDTO());
        });
    }

    @Test
    public void shouldReturnTransactionsByAccountIdAndDateRange() {
        Long accountId = 1L;
        Instant startDate = Instant.now();
        Instant endDate = Instant.now();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createTransaction(1L));
        when(transactionRepository.findAllByAccountIdAndCreatedAtBetween(accountId, startDate, endDate))
                .thenReturn(transactions);

        List<Transaction> result = transactionService.findAllByAccountIdAndCreatedAtBetween(accountId, startDate,
                endDate);

        Assertions.assertEquals(transactions.size(), result.size());
    }
}