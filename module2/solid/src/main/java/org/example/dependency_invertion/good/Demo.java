package org.example.dependency_invertion.good;

import org.example.dependency_invertion.User;

public class Demo {
    public static void main(String[] args) {
        Database dynamoDB = new DynamoDB();
        UserService userService = new UserService(dynamoDB);
        userService.saveUser(new User("John"));

        Database mySqlDatabase = new MySqlDatabase();
        UserService userService2 = new UserService(mySqlDatabase);
        userService2.saveUser(new User("Jane"));
    }
}
