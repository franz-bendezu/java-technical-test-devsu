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

import com.devsu.bank.account_service.dto.TransactionCreateDTO;
import com.devsu.bank.account_service.model.Transaction;
import com.devsu.bank.account_service.service.TransactionService;

@RestController
@RequestMapping("/movements")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService movementService) {
        this.transactionService = movementService;
    }

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction save(@RequestBody TransactionCreateDTO movement) {
        return transactionService.create(movement);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody TransactionCreateDTO movement) {
        return transactionService.updateById(id, movement);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
    }
}
