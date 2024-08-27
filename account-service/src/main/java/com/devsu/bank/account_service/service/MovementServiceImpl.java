package com.devsu.bank.account_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.bank.account_service.model.Movement;
import com.devsu.bank.account_service.repository.MovementRepository;


@Service
public class MovementServiceImpl implements MovementService {
    private MovementRepository movementRepository;

    public MovementServiceImpl(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    @Override
    public Movement findById(Long id) {
        return movementRepository.findById(id).orElse(null);
    }

    @Override
    public Movement save(Movement movement) {
        return movementRepository.save(movement);
    }

    @Override
    public void deleteById(Long id) {
        movementRepository.deleteById(id);
    }
    
}
