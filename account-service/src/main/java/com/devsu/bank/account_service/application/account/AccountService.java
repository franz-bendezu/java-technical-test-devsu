package com.devsu.bank.account_service.application.account;

import com.devsu.bank.account_service.adapters.dto.AccountBaseDTO;
import com.devsu.bank.account_service.domain.model.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();

    public Account findById(Long id);

    public Account create(AccountBaseDTO account);

    public void deleteById(Long id);

    public Account updateById(Long id, AccountBaseDTO account);
}
