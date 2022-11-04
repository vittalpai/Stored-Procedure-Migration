package com.storedproc.demo;

import java.util.List;

public abstract class DatabaseService {
    private String type;

    public String getType() {
        return this.type;
    }

    public abstract Iterable findAllCustomers();
    public abstract Iterable findCustomers(String firstName, String phone);
}
