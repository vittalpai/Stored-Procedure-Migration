package com.storedproc.demo.oracle;


import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.sql.ResultSet;


@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "Customer.search", procedureName = "SEARCHPERSON", parameters = {@StoredProcedureParameter(type = ResultSet.class, mode = ParameterMode.REF_CURSOR)})})

@Entity
@Table(name = "USERS")
@Profile(DatabaseTypes.ORACLE)
public class Customer {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstName;

    @Column(name = "LASTNAME", length = 50)
    private String lastName;

    @Column(name = "AGE", length = 50)
    private Long age;

    @Column(name = "PHONE", length = 50)
    private String phone;

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, Long age, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
