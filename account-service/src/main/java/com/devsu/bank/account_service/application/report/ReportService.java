package com.devsu.bank.account_service.application.report;

import java.time.LocalDate;

import com.devsu.bank.account_service.adapters.dto.ReportStatementAccountDTO;

public interface ReportService {
    public ReportStatementAccountDTO getAccountStatement(Long clientId, LocalDate start, LocalDate end);
}
