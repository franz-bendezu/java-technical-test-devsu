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

import com.devsu.bank.account_service.dto.AccountCreateDTO;
import com.devsu.bank.account_service.dto.AccountDTO;
import com.devsu.bank.account_service.model.Account;
import com.devsu.bank.account_service.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AccountController.PATH)
public class AccountController {

    public static final String PATH = "/accounts";

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping
    public AccountDTO save(@Valid @RequestBody AccountCreateDTO account) {
        return accountService.create(account);
    }

    @PutMapping("/{id}")
    public AccountDTO update(@PathVariable Long id, @Valid @RequestBody AccountCreateDTO account) {
        return accountService.updateById(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
