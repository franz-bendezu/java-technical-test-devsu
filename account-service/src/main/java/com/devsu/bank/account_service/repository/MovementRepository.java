package com.devsu.bank.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.bank.account_service.model.Movement;

@Repository
public interface MovementRepository  extends JpaRepository<Movement, Long> {
    
}
