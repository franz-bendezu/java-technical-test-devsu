package com.devsu.bank.client_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.bank.client_service.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
