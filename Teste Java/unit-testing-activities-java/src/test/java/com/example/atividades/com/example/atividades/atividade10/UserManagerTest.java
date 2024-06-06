package com.example.atividades.atividade10;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class UserManagerTest {

    private UserService mockUserService;
    private UserManager userManager;

    @Before
    public void setUp() {
        mockUserService = Mockito.mock(UserService.class);
        userManager = new UserManager(mockUserService);
    }

    @Test
    public void testFetchUserInfoValidUser() {
        User mockUser = new User("John Doe", "john@example.com");
        when(mockUserService.getUserInfo(1)).thenReturn(mockUser);

        User user = userManager.fetchUserInfo(1);
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testFetchUserInfoUserNotFound() {
        when(mockUserService.getUserInfo(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userManager.fetchUserInfo(1));
    }
}
