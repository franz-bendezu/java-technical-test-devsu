package com.devsu.bank.account_service.adapters.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.account_service.adapters.dto.ReportStatementAccountDTO;
import com.devsu.bank.account_service.application.report.ReportService;

@RestController
@RequestMapping(ReportController.PATH)
public class ReportController {
    private final ReportService reportService;

    public static final String PATH = "/reportes";

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public ReportStatementAccountDTO getAccountStatement(@RequestParam(value = "cliente") Long clientId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "rangoInicio") LocalDate start,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "rangoFin") LocalDate end) {
        return reportService.getAccountStatement(clientId, start, end);
    }
}
