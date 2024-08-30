package com.devsu.bank.account_service.application.report;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Map;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.adapters.dto.ClientDTO;
import com.devsu.bank.account_service.adapters.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.adapters.dto.StatementAccountDTO;
import com.devsu.bank.account_service.adapters.mapper.TransactionMapper;
import com.devsu.bank.account_service.application.client.ClientService;
import com.devsu.bank.account_service.application.transaction.TransactionService;
import com.devsu.bank.account_service.domain.model.Transaction;
import com.devsu.bank.account_service.infraestructure.config.CommonSettings;
import com.devsu.bank.account_service.infraestructure.repository.AccountRepository;

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

        Map<Long, List<Transaction>> transactionsByAccountId = allTransactions.stream()
                .collect(Collectors.groupingBy(
                        (Transaction transaction) -> {
                            return transaction.getAccount().getId();
                        }));

        List<StatementAccountDTO> accounts = accountRepository.findAllByClientId(clientId).stream().map(account -> {
            List<Transaction> transactions = transactionsByAccountId.getOrDefault(account.getId(),
                    Collections.emptyList());

            // TODO: Analizar si deberia usar saldo del rango de fechas o el saldo actual
            Integer balance = transactions.stream()
                    .sorted(Comparator.comparing(Transaction::getCreatedAt).reversed())
                    .findFirst()
                    .map(Transaction::getBalance)
                    .orElse(account.getInitialAmount());
            StatementAccountDTO accountDTO = new StatementAccountDTO();
            accountDTO.setId(account.getId());
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setCurrentAmount(balance);
            accountDTO.setTransactions(
                    transactions.stream()
                            .map(TransactionMapper::toDTO)
                            .collect(Collectors.toList()));

            return accountDTO;
        }).collect(Collectors.toList());

        accountStatementDTO.setAccounts(accounts);
        return accountStatementDTO;
    }
}