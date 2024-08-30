package com.devsu.bank.account_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.model.Account;

public class AccountMapper {
    public static AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setInitialAmount(account.getInitialAmount());
        accountDTO.setStatus(account.isStatus());
        accountDTO.setClientId(account.getClientId());
        accountDTO.setId(account.getId());

        return accountDTO;
    }

    public static List<AccountDTO> toDTO(List<Account> accounts) {
        return accounts.stream().map(AccountMapper::toDTO).collect(Collectors.toList());
    }

}
