package com.example.SunbaseDataassignment.api.services;

import com.example.SunbaseDataassignment.api.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    @Transactional
    Integer deleteById(UUID id);

    boolean existsById(UUID id);
}
