package org.example.dependency_invertion.good;

import org.example.dependency_invertion.User;

public class MySqlDatabase implements Database {
    @Override
    public void save(User user) {
        System.out.println("Saving user to MySQL database");
    }
}
