package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.StatementAccountDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
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
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(this::toAccountDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id).map(this::toAccountDTO).orElseThrow(

                () -> new RuntimeException("Account with id " + id + " not found"));
    }

    @Override
    public AccountDTO create(AccountCreateDTO accountCreateDTO) {
        Account account = new Account();
        account.setClientId(accountCreateDTO.getClientId());
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        Account savedAccount = accountRepository.save(account);
        return toAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO updateById(Long id, AccountCreateDTO accountCreateDTO) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account with id " + id + " not found"));
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        Account savedAccount = accountRepository.save(account);
        return toAccountDTO(savedAccount);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public ReportStatementAccountDTO getAccountStatement(Long clientId, Instant startDate, Instant endDate) {

        ReportStatementAccountDTO accountStatementDTO = new ReportStatementAccountDTO();
        // TODO: Implementar la lógica para obtener el nombre del cliente

        List<StatementAccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {
            StatementAccountDTO accountDTO = new StatementAccountDTO();
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

    private AccountDTO toAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setInitialAmount(account.getInitialAmount());
        accountDTO.setStatus(account.isStatus());
        accountDTO.setClientId(account.getClientId());
        return accountDTO;
    }

}
