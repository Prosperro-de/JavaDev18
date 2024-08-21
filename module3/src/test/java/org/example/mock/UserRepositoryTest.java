package org.example.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userRepository.save(new User(1, "Mykola"));
        userRepository.save(new User(2, "Oleg"));
        userRepository.save(new User(3, "Olena"));
    }

    @Test
    void createUserTest() {
        User stepan = new User(4, "Stepan");
        assertDoesNotThrow(() -> userRepository.save(stepan));
    }

    @Test
    void findUserByIdTest() {
        User user = userRepository.findById(1);
        assertEquals(new User(1, "Mykola"), user);
    }

    @Test
    void deleteUserByIdTest() {
        assertDoesNotThrow(() -> userRepository.deleteById(1));
    }

    @Test
    void isUserExistTest() {
        assertTrue(userRepository.isExist(1));
    }

}