package com.devsu.bank.account_service.controller;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;
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
    public ReportStatementAccountDTO getAccountStatement(@RequestParam Long clientId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate  start,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate end) {
        return accountService.getAccountStatement(clientId, start, end);
    }
}
