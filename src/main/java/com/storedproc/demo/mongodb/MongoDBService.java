package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseService;
import com.storedproc.demo.DatabaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(DatabaseTypes.MONGODB)
@Service(value = DatabaseTypes.MONGODB)
public class MongoDBService extends DatabaseService {

    @Autowired
    private UserRepository repository;

    @Override
    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Iterable findUser(String firstName, String lastName) {
        return repository.findUser(firstName, lastName);
    }

}
