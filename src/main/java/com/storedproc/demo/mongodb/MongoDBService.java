package com.storedproc.demo.mongodb;
import com.storedproc.demo.DatabaseService;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile(DatabaseTypes.MONGODB)
@Service(value = DatabaseTypes.MONGODB)
public class MongoDBService extends DatabaseService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Iterable<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Iterable findCustomers(String firstName, String phone) {
        return repository.findCustomers(firstName, phone);
    }
}