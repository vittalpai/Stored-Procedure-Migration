package com.storedproc.demo.oracle;

import com.storedproc.demo.CustomersDao;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = DatabaseTypes.ORACLE)
@Profile(DatabaseTypes.ORACLE)
public class CustomersDaoImpl extends CustomersDao {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Iterable findAllUsers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Iterable findUsers(String occupation, String zipcode) {
        return repository.findUsers(occupation, zipcode);
    }
}
