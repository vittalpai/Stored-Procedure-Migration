package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Profile(DatabaseTypes.MONGODB)
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Aggregation(pipeline = {"{ '$match': { '$expr': { '$cond': { if: { '$and': [{ '$ne': [ ?0, null]},{ '$ne': [ ?1, null] }]}, then: { '$and': [{ '$eq': ['$firstName', ?0]},{'$eq': ['$phone',?1]}]}, else: { '$cond': { if: {'$ne': [?0, null]}, then: {'$eq': ['$firstName', ?0]}, else: {'$eq': ['$phone', ?1]}}}}}}}",})
    List<Customer> findCustomers(String firstName, Long phone);

}
