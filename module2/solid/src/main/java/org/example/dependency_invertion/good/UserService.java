package org.example.dependency_invertion.good;

import org.example.dependency_invertion.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void saveUser(User user) {
        database.save(user);
    }
}
