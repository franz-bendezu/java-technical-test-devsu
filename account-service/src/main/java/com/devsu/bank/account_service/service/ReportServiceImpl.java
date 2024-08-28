package com.devsu.bank.account_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.dto.StatementAccountDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.repository.AccountRepository;

public class ReportServiceImpl implements ReportService {
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    public ReportServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public ReportStatementAccountDTO getAccountStatement(Long clientId, LocalDate start, LocalDate end) {

        ReportStatementAccountDTO accountStatementDTO = new ReportStatementAccountDTO();
        // TODO: Implementar la lógica para obtener el nombre del cliente

        List<StatementAccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {
            StatementAccountDTO accountDTO = new StatementAccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setInitialAmount(account.getInitialAmount());

            List<TransactionDTO> transactions = transactionService.findAllByAccountIdAndCreatedAtBetween(
                    account.getId(),
                    start.atStartOfDay(CommonSettings.TIME_ZONE).toInstant(),
                    end.plusDays(1).atStartOfDay(CommonSettings.TIME_ZONE).toInstant());
            accountDTO.setTransactions(transactions);

            return accountDTO;
        }).collect(Collectors.toList());

        accountStatementDTO.setAccounts(accounts);
        return accountStatementDTO;
    }
}