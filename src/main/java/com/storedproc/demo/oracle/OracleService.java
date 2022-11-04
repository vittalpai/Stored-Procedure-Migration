package com.storedproc.demo.oracle;
import com.storedproc.demo.DatabaseService;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service(value = DatabaseTypes.ORACLE)
@Profile(DatabaseTypes.ORACLE)
public class OracleService extends DatabaseService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager entity;

    @Override
    public Iterable findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Iterable findCustomers(String firstName, String phone) {
        return repository.findCustomers(firstName, phone, entity);
    }
}
