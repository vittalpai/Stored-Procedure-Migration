package com.storedproc.demo;

public abstract class CustomersDao {
    private String type;

    public String getType() {
        return this.type;
    }

    public abstract Iterable findAllUsers();
    public abstract Iterable findUsers(String occupation, String zipcode);
}
