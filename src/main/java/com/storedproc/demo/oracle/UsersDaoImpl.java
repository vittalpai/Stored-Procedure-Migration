package com.storedproc.demo.oracle;

import com.storedproc.demo.UsersDao;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = DatabaseTypes.ORACLE)
@Profile(DatabaseTypes.ORACLE)
public class UsersDaoImpl extends UsersDao {

    @Autowired
    private UserRepository repository;

    @Override
    public Iterable findAllUsers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Iterable findUser(String firstName, String lastName) {
        return repository.findUser(firstName, lastName);
    }
}
