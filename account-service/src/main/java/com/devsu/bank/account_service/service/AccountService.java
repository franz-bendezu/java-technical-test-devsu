package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.model.Account;

import java.time.Instant;
import java.util.List;

public interface AccountService {
    public List<Account> findAll();

    public Account findById(Long id);

    public Account create(AccountCreateDTO account);

    public void deleteById(Long id);

    public ReportStatementAccountDTO getAccountStatement(Long clientId, Instant startDate, Instant endDate);

    public Account updateById(Long id, AccountCreateDTO account);
}
