package com.devsu.bank.account_service.repository;

import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClientId(Long clientId);

}