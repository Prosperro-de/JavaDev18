package org.example.mock;

public class UserRepoProxy extends UserRepository {

    @Override
    public User findById(Integer id) {
        if (id == 1) {
            return new User(1, "Mykola");
        }
        super.findById(id);

        return null;
    }
}
