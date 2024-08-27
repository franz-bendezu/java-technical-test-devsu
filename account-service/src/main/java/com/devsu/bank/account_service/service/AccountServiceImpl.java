package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.AccountStatementDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountStatementDTO getAccountStatement(Long clientId, Instant startDate, Instant endDate) {

        AccountStatementDTO accountStatementDTO = new AccountStatementDTO();
        // TODO: Implementar la lógica para obtener el nombre del cliente

        List<AccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setInitialAmount(account.getInitialAmount());

            List<TransactionDTO> transactions = transactionService.findAllByAccountIdAndCreatedAtBetween(
                    account.getId(), startDate,
                    endDate);
            accountDTO.setTransactions(transactions);

            return accountDTO;
        }).collect(Collectors.toList());

        accountStatementDTO.setAccounts(accounts);
        return accountStatementDTO;
    }

}
