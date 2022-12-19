package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Profile(DatabaseTypes.MONGODB)
public class Customer {

    @Id
    public String id;

    @Field("firstName")
    public String firstName;

    @Field("lastName")
    public String lastName;

    @Field("age")
    public Number age;

    @Field("phone")
    public Long phone;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Number age, Long phone) {
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

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}

