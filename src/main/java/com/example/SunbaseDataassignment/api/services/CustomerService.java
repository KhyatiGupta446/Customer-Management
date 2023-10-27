package com.example.SunbaseDataassignment.api.services;

import com.example.SunbaseDataassignment.api.models.Customer;
import com.example.SunbaseDataassignment.api.services.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return (List<Customer>) customerRepository.findAll();
    }

    public boolean deleteCustomer(UUID id){
        return customerRepository.deleteById(id) > 0;
    }

    public void updateCustomer(UUID id, Customer customer){
        customer.setId(id);
        customerRepository.save(customer);
    }

    public boolean isIdPresent(UUID id){
        return customerRepository.existsById(id);
    }
}
