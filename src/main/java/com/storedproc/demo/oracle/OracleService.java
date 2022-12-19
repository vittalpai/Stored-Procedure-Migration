package com.storedproc.demo.oracle;

import com.storedproc.demo.DatabaseService;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = DatabaseTypes.ORACLE)
@Profile(DatabaseTypes.ORACLE)
public class OracleService extends DatabaseService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Iterable findAllCustomers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Iterable findCustomers(String firstName, String lastName) {
        return repository.findCustomers(firstName, lastName);
    }
}
