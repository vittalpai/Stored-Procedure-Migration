package com.example.migrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomerRest {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/all")
    public List<Customer> getEmployee() {
        return repository.findCustomersByFirstNameAndPhone("Alice", Long.parseLong("9738557557"));
    }

}

