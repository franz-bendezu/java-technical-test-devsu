package com.devsu.bank.account_service.infraestructure.repository;

import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.domain.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClientId(Long clientId);

}