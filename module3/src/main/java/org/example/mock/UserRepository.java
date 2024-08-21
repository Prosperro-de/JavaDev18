package org.example.mock;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> dataBase = new HashMap<>();

    public void save(User user) {
        dataBase.put(user.getId(), user);
    }

    public User findById(Integer id) {
        return dataBase.get(id);
    }

    public boolean isExist(Integer id) {
        return dataBase.containsKey(id);
    }

    public void deleteById(Integer id) {
        dataBase.remove(id);
    }
}
