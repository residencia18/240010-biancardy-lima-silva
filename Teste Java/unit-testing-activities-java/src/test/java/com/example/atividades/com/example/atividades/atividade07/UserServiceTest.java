package com.example.atividades.atividade07;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class UserServiceTest {

    private Database mockDatabase;
    private UserService userService;

    @Before
    public void setUp() {
        mockDatabase = Mockito.mock(Database.class);
        userService = new UserService(mockDatabase);
    }

    @Test
    public void testSaveUserWithValidData() {
        User user = new User("John Doe", "john@example.com");
        userService.saveUser(user);
        verify(mockDatabase, times(1)).saveUser(user);
    }

    @Test
    public void testSaveUserWithNullName() {
        User user = new User(null, "john@example.com");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithEmptyName() {
        User user = new User("", "john@example.com");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithNullEmail() {
        User user = new User("John Doe", null);
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithEmptyEmail() {
        User user = new User("John Doe", "");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }
}
