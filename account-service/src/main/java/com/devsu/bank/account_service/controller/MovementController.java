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

import com.devsu.bank.account_service.model.Movement;
import com.devsu.bank.account_service.service.MovementService;

@RestController
@RequestMapping("/movements")
public class MovementController {

    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public List<Movement> findAll() {
        return movementService.findAll();
    }

    @GetMapping("/{id}")
    public Movement findById(@PathVariable Long id) {
        return movementService.findById(id);
    }

    @PostMapping
    public Movement save(@RequestBody Movement movement) {
        return movementService.save(movement);
    }

    @PutMapping("/{id}")
    public Movement update(@PathVariable Long id, @RequestBody Movement movement) {
        movement.setId(id);
        return movementService.save(movement);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        movementService.deleteById(id);
    }
}
