package com.devsu.bank.account_service.service;

import java.util.List;

import com.devsu.bank.account_service.model.Movement;

public interface MovementService {

    public List<Movement> findAll();

    public Movement findById(Long id);

    public Movement save(Movement movement);

    public void deleteById(Long id);
}
