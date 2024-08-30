package com.devsu.bank.account_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.account_service.dto.TransactionBaseDTO;
import com.devsu.bank.account_service.dto.TransactionDTO;
import com.devsu.bank.account_service.mapper.TransactionMapper;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(TransactionController.PATH)
public class TransactionController {

    public static final String PATH = "/movimientos";

    private TransactionService transactionService;

    public TransactionController(TransactionService movementService) {
        this.transactionService = movementService;
    }

    @GetMapping
    public List<TransactionDTO> findAll() {
        return TransactionMapper.toDTO(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public TransactionDTO findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public TransactionDTO save(@Valid @RequestBody TransactionBaseDTO movement) {
        return TransactionMapper.toDTO(transactionService.create(movement));
    }

    @PutMapping("/{id}")
    public TransactionDTO update(@PathVariable Long id, @Valid @RequestBody TransactionBaseDTO movement) {
        return TransactionMapper.toDTO(transactionService.updateById(id, movement));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
    }
}
