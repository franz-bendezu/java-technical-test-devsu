package com.devsu.bank.account_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.dto.AccountBaseDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.ClientDTO;
import com.devsu.bank.account_service.exception.AccountNotFoundException;
import com.devsu.bank.account_service.mapper.AccountMapper;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private ClientService clientService;

    public AccountServiceImpl(AccountRepository accountRepository, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
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
    public Account create(AccountBaseDTO accountCreateDTO) {
        Account account = new Account();
        ClientDTO client = clientService.findById(accountCreateDTO.getClientId());
        account.setClientId(client.getId());
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        return accountRepository.save(account);
    }

    @Override
    public Account updateById(Long id, AccountBaseDTO accountCreateDTO) {
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
