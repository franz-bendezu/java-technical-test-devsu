package com.devsu.bank.client_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.bank.client_service.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
