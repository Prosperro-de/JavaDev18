package org.example.dependency_invertion.bad;

import org.example.dependency_invertion.User;

public class UserService {
    private MySqlDataBase mySqlDataBase = new MySqlDataBase();
    private DynamoDB dynamoDB = new DynamoDB();

    public void saveUser(User user) {
        mySqlDataBase.save(user);
    }

    public void saveUserToDynamoDb(User user) {
        dynamoDB.save(user);
    }
}
