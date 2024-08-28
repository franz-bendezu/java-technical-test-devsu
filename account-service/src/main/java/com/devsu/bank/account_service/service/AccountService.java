package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.model.Account;

import java.time.LocalDate;
import java.util.List;

public interface AccountService {
    public List<AccountDTO> findAll();

    public Account findById(Long id);

    public Account create(AccountCreateDTO account);

    public void deleteById(Long id);

    public ReportStatementAccountDTO getAccountStatement(Long clientId, LocalDate start, LocalDate end);

    public Account updateById(Long id, AccountCreateDTO account);
}
