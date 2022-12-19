package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Profile(DatabaseTypes.MONGODB)
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Aggregation(pipeline = {"{ '$match': { 'firstName': ?0, 'lastName': ?1 } }","{ '$project': { '_id': 1, 'firstName': 1, 'lastName': 1, 'phone' : 1, 'age' : 1, 'cardNumber': { '$concat': ['XXXXXXXXXXXX', { '$substrCP': ['$cardNumber', 12, 4] }] } } }"})
    List<Customer> findCustomers(String firstName, String lastName);

}
