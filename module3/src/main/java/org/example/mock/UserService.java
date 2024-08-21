package org.example.mock;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        userRepository.save(user);
    }

    public User findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return userRepository.findById(id);
    }

    public void deleteById(Integer id) {
        if (!userRepository.isExist(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
