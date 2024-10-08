package com.devsu.bank.client_service.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.bank.client_service.domain.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
