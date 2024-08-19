package org.example.dependency_invertion.bad;

import org.example.dependency_invertion.User;

public class MySqlDataBase {
    void save(User data) {
        System.out.println("Saving data to MySQL database: " + data);
    }
}
