package com.devsu.bank.account_service.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.account_service.dto.AccountStatementDTO;
import com.devsu.bank.account_service.service.AccountService;

@RestController
@RequestMapping(ReportController.PATH)
public class ReportController {
    private final AccountService accountService;

    public static final String PATH = "/reports";

    public ReportController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public AccountStatementDTO getAccountStatement(@RequestParam Long clientId, @RequestParam Instant startDate,
            @RequestParam Instant endDate) {
        return accountService.getAccountStatement(clientId, startDate, endDate);
        
    }
}
