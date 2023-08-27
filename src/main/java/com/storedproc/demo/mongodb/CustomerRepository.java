package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Profile(DatabaseTypes.MONGODB)
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Aggregation(pipeline = {
            "{ '$match': { 'Occupation': ?0, 'ZipCode': ?1 } }",
            "{ '$project': { 'Name': 1, 'PhoneNumber': 1, 'Card Number': { '$concat': ['XXXXXXXXXXXX', { '$substrCP': ['$Card Number', 12, 4] }] } } }",
            "{ '$sort': { 'Name': 1 } }",
            "{ '$limit': 10 }"
    })
    List<Customer> findUsers(String occupation, String zipcode);

}
