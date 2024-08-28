package com.devsu.bank.account_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.StatementAccountDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.exception.AccountNotFoundException;
import com.devsu.bank.account_service.mapper.AccountMapper;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(AccountMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account create(AccountCreateDTO accountCreateDTO) {
        Account account = new Account();
        account.setClientId(accountCreateDTO.getClientId());
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        return accountRepository.save(account);
    }

    @Override
    public Account updateById(Long id, AccountCreateDTO accountCreateDTO) {
        Account account = this.findById(id);
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

}
