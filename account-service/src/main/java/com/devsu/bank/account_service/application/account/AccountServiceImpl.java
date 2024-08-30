package com.devsu.bank.account_service.application.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.adapters.dto.AccountBaseDTO;
import com.devsu.bank.account_service.adapters.dto.ClientDTO;
import com.devsu.bank.account_service.application.client.ClientService;
import com.devsu.bank.account_service.domain.exception.AccountNotFoundException;
import com.devsu.bank.account_service.domain.model.Account;
import com.devsu.bank.account_service.infraestructure.repository.AccountRepository;
import com.devsu.bank.account_service.infraestructure.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private ClientService clientService;

    public AccountServiceImpl(AccountRepository accountRepository, ClientService clientService,
            TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account create(AccountBaseDTO accountCreateDTO) {
        ClientDTO client = clientService.findById(accountCreateDTO.getClientId());
        Account account = new Account();
        account.setClientId(client.getId());
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        return accountRepository.save(account);
    }

    @Override
    public Account updateById(Long id, AccountBaseDTO accountCreateDTO) {
        Account account = this.findById(id);
        account.setAccountNumber(accountCreateDTO.getAccountNumber());
        account.setAccountType(accountCreateDTO.getAccountType());
        account.setInitialAmount(accountCreateDTO.getInitialAmount());
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new AccountNotFoundException();
        }
        // TODO: ¿Se debe eliminar las transacciones? o se debe hacer un soft delete incluyendo la cuenta
        transactionRepository.deleteAllByAccountId(id);
        accountRepository.deleteById(id);
    }

}
