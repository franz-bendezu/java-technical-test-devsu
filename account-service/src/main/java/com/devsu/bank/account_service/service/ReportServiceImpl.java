package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.ClientDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.dto.StatementAccountDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.repository.AccountRepository;

@Service
public class ReportServiceImpl implements ReportService {
    private AccountRepository accountRepository;
    private TransactionService transactionService;
    private ClientService clientService;

    public ReportServiceImpl(AccountRepository accountRepository, TransactionService transactionService,
            ClientService clientService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.clientService = clientService;
    }

    @Override
    public ReportStatementAccountDTO getAccountStatement(Long clientId, LocalDate start, LocalDate end) {

        ReportStatementAccountDTO accountStatementDTO = new ReportStatementAccountDTO();
  
        ClientDTO client = clientService.getClient(clientId);
        accountStatementDTO.setCustomerName(client.getName());

        Instant startTransaction = start.atStartOfDay(CommonSettings.TIME_ZONE).toInstant();
        Instant endTransaction = end.plusDays(1).atStartOfDay(CommonSettings.TIME_ZONE).toInstant();
        List<StatementAccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {

            List<TransactionDTO> transactions = transactionService.findAllByAccountIdAndCreatedAtBetween(
                    account.getId(),
                    startTransaction,
                    endTransaction);
            StatementAccountDTO accountDTO = new StatementAccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setInitialAmount(account.getInitialAmount());
            accountDTO.setTransactions(transactions);

            return accountDTO;
        }).collect(Collectors.toList());

        accountStatementDTO.setAccounts(accounts);
        return accountStatementDTO;
    }
}