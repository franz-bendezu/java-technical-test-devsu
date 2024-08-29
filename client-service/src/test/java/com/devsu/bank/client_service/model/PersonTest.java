package com.devsu.bank.client_service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PersonTest {

    @Test
    public void testPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("John Doe");
        person.setGender("Male");
        person.setAge(30);
        person.setIdentification("123456789");
        person.setAddress("123 Main St");
        person.setPhone("555-1234");

        Assertions.assertEquals(1L, person.getId());
        Assertions.assertEquals("John Doe", person.getName());
        Assertions.assertEquals("Male", person.getGender());
        Assertions.assertEquals(30, person.getAge());
        Assertions.assertEquals("123456789", person.getIdentification());
        Assertions.assertEquals("123 Main St", person.getAddress());
        Assertions.assertEquals("555-1234", person.getPhone());
    }
}