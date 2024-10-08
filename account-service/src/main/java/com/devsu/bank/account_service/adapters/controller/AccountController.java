package com.devsu.bank.account_service.adapters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.bank.account_service.adapters.dto.AccountBaseDTO;
import com.devsu.bank.account_service.adapters.dto.AccountDTO;
import com.devsu.bank.account_service.adapters.mapper.AccountMapper;
import com.devsu.bank.account_service.application.account.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AccountController.PATH)
public class AccountController {

    public static final String PATH = "/cuentas";

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> findAll() {
        return AccountMapper.toDTO(accountService.findAll());
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable Long id) {
        return AccountMapper.toDTO(accountService.findById(id));
    }

    @PostMapping
    public AccountDTO save(@Valid @RequestBody AccountBaseDTO account) {
        return AccountMapper.toDTO(accountService.create(account));
    }

    @PutMapping("/{id}")
    public AccountDTO update(@PathVariable Long id, @Valid @RequestBody AccountBaseDTO account) {
        return AccountMapper.toDTO(accountService.updateById(id, account));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
