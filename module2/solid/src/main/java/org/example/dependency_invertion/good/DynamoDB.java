package org.example.dependency_invertion.good;

import org.example.dependency_invertion.User;

public class DynamoDB implements Database {
    @Override
    public void save(User user) {
        System.out.println("Saving user to DynamoDB");
    }
}
