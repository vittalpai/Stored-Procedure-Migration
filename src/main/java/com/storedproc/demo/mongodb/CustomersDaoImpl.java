package com.storedproc.demo.mongodb;

import com.storedproc.demo.CustomersDao;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(DatabaseTypes.MONGODB)
@Service(value = DatabaseTypes.MONGODB)
public class CustomersDaoImpl extends CustomersDao {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Iterable<Customer> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Iterable findUsers(String occupation, String zipcode) { return repository.findUsers(occupation, zipcode);}

}
