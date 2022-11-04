package com.storedproc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping(value = "/customer")
public class DatabaseRest {

    @Autowired
    Map<String, DatabaseService> dbService;

    @Value("${spring.profiles.active}")
    private String databaseType;

    @RequestMapping(value = "/all")
    public Iterable getAllCustomers() {
        return dbService.get(databaseType).findAllCustomers();
    }

    @RequestMapping(value = "/search")
    public Iterable getCustomer(@RequestParam(required = false) String firstname,@RequestParam(required = false) String phone) {
        return dbService.get(databaseType).findCustomers(firstname, phone);
    }

}

