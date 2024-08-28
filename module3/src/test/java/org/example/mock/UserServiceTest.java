package org.example.mock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
//        userRepository = Mockito.mock(UserRepository.class);
//        userService = new UserService(userRepository);
    }

    @Test
    void saveUserSuccessfullyTest() {
        User user = new User(1, "Mykola");
        assertDoesNotThrow(() -> userService.save(user));
    }

    @Test
    void findUserByIdTest() {
        User expectedUser = new User(1, "Mykola");

        when(userRepository.findById(1)).thenReturn(expectedUser);

        User userById = userService.findById(1);

        assertNotNull(userById);
        assertEquals(expectedUser, userById);
    }

//    @Test
//    void findUserByIdTest() {
//        assertDoesNotThrow(() -> userService.findById(1));
//        verify(userRepository).findById(1);
//    }

    @Test
    void findUserByIdFailsIfIdNullTest() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.findById(null));
        verify(userRepository, times(0)).findById(
                Mockito.any());
    }

    public static void main(String[] args) {
        String[] s = "Java    is     the best".split(" ");
        System.out.println(Arrays.toString(s));
    }
}