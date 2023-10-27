package com.example.SunbaseDataassignment.api.controllers;

import com.example.SunbaseDataassignment.api.services.CustomerService;
import com.example.SunbaseDataassignment.api.models.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        if(customer.getFirst_name() == null || customer.getLast_name()==null){
            return ResponseEntity.badRequest().body("First Name or Last Name is missing");
        }
        customerService.addCustomer(customer);
        return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }

    @GetMapping("/get_customer_list")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam UUID uuid){
        if(!customerService.isIdPresent(uuid)){
            return ResponseEntity.badRequest().body("UUID not found");
        }
        boolean isDeleted= customerService.deleteCustomer(uuid);

        if(isDeleted) {
            return ResponseEntity.ok().body("Successfully Deleted");
        }else{
            return ResponseEntity.internalServerError().body("Error Not Deleted");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestParam UUID uuid, @RequestBody(required = false) String cust) throws JsonProcessingException {
        if(!customerService.isIdPresent(uuid)){
            return ResponseEntity.internalServerError().body("UUID not found");
        }
        if(cust==null || cust.isEmpty() || cust.equals("{}")){
            return ResponseEntity.badRequest().body("Body is Empty");
        }
        ObjectMapper objectMapper= new ObjectMapper();
        Customer customer = objectMapper.readValue(cust,Customer.class);
        customerService.updateCustomer(uuid,customer);
        return ResponseEntity.ok().body("Successfully Updated");
    }
}
