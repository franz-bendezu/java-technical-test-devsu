package com.devsu.bank.client_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.bank.client_service.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
