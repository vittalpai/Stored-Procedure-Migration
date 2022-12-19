package com.storedproc.demo;

public abstract class DatabaseService {
    private String type;

    public String getType() {
        return this.type;
    }

    public abstract Iterable findAllUsers();
    public abstract Iterable findUser(String firstName, String phone);
}
