package com.devsu.bank.account_service.service;

import java.time.LocalDate;

import com.devsu.bank.account_service.dto.ReportStatementAccountDTO;

public interface ReportService {
    public ReportStatementAccountDTO getAccountStatement(Long clientId, LocalDate start, LocalDate end);
}
