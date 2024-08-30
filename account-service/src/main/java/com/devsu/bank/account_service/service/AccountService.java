package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.AccountBaseDTO;
import com.devsu.bank.account_service.model.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();

    public Account findById(Long id);

    public Account create(AccountBaseDTO account);

    public void deleteById(Long id);

    public Account updateById(Long id, AccountBaseDTO account);
}
