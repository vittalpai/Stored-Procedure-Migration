package com.storedproc.demo.oracle;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Profile(DatabaseTypes.ORACLE)
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    default Iterable<Customer> findCustomers(String firstName, String phone, EntityManager entity) {
        StoredProcedureQuery query = entity.createStoredProcedureQuery("SEARCHPERSON1", Customer.class);
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Customer.class, ParameterMode.REF_CURSOR);
        query.setParameter(1, getString(firstName));
        query.setParameter(2, getString(phone));
        query.execute();
        return query.getResultList();
    }

    default String getString(String variable) {
        if (variable == null || variable.isEmpty()) {
            return variable;
        }
        variable = new StringBuilder("'").append(variable).append("'").toString();
        return  variable;
    }
}
