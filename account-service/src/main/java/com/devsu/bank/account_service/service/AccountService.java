package com.devsu.bank.account_service.service;

import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;

import java.time.Instant;
import java.util.List;

public interface AccountService {
    public List<AccountDTO> findAll();

    public AccountDTO findById(Long id);

    public AccountDTO create(AccountCreateDTO account);

    public void deleteById(Long id);

    public ReportStatementAccountDTO getAccountStatement(Long clientId, Instant startDate, Instant endDate);

    public AccountDTO updateById(Long id, AccountCreateDTO account);
}
