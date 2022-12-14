package com.storedproc.demo.oracle;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile(DatabaseTypes.ORACLE)
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Procedure(name = "CARDMASK_SP")
    List findUser(@Param("FIRSTNAME") String firstName, @Param("LASTNAME") String lastName);

}
