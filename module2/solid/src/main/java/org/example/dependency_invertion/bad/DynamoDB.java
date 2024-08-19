package org.example.dependency_invertion.bad;

import org.example.dependency_invertion.User;
import org.example.dependency_invertion.good.Database;

public class DynamoDB {

    public void save(User user) {
        System.out.println("Saving user to DynamoDB");
    }
}
