package com.devsu.bank.account_service.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Map;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.config.CommonSettings;
import com.devsu.bank.account_service.dto.ClientDTO;
import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.dto.StatementAccountDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.repository.AccountRepository;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.mapper.TransactionMapper;

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

        ClientDTO client = clientService.findById(clientId);
        accountStatementDTO.setCustomerName(client.getName());

        Instant startTransaction = start.atStartOfDay(CommonSettings.TIME_ZONE).toInstant();
        Instant endTransaction = end.plusDays(1).atStartOfDay(CommonSettings.TIME_ZONE).toInstant();
        List<Transaction> allTransactions = transactionService.findAllByClientIdAndCreatedAtBetween(
                clientId, startTransaction, endTransaction);

        Map<Long, List<TransactionDTO>> transactionsByAccountId = allTransactions.stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.groupingBy(TransactionDTO::getAccountId));

        List<StatementAccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {
            List<TransactionDTO> transactions = transactionsByAccountId.getOrDefault(account.getId(),
                    Collections.emptyList());

            // TODO: Analizar si deberia usar saldo del rango de fechas o el saldo actual
            Integer balance = transactions.stream()
                    .sorted(Comparator.comparing(TransactionDTO::getCreatedDate).reversed()) // Sort by date descending
                    .findFirst()
                    .map(TransactionDTO::getBalance)
                    .orElse(account.getInitialAmount());
            StatementAccountDTO accountDTO = new StatementAccountDTO();
            accountDTO.setId(account.getId());
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setInitialAmount(account.getInitialAmount());
            accountDTO.setCurrentAmount(balance);
            accountDTO.setTransactions(transactions);

            return accountDTO;
        }).collect(Collectors.toList());

        accountStatementDTO.setAccounts(accounts);
        return accountStatementDTO;
    }
}