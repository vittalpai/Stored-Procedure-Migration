package com.storedproc.demo.mongodb;

import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Profile(DatabaseTypes.MONGODB)
public class Customer {

    @Id
    public String id;

    @Field("Name")
    public String name;

    @Field("PhoneNumber")
    public String phoneNumber;

    @Field("Card Number")
    public String cardNumber;

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String cardNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
