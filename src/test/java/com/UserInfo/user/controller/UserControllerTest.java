package com.UserInfo.user.controller;

import com.UserInfo.user.controller.UserController;
import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testLoadUsers() {
//        when(userService.loadUsersFromExternalAPI()).thenReturn(Collections.emptyList());
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        List<UserDTO> result = userController.loadUsers();

        verify(userService, times(1)).loadUsersFromExternalAPI();
        verify(userService, times(1)).getAllUsers();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetUsersByRole() {
        String role = "admin";
        List<UserDTO> users = List.of(new UserDTO());

        when(userService.getUsersByRole(role)).thenReturn(users);

        List<UserDTO> result = userController.getUsersByRole(role);

        verify(userService, times(1)).getUsersByRole(role);
        assertEquals(users, result);
    }

    @Test
    public void testGetUsersSortedByAge() {
        boolean ascending = true;
        List<UserDTO> users = List.of(new UserDTO());

        when(userService.getUsersSortedByAge(ascending)).thenReturn(users);

        List<UserDTO> result = userController.getUsersSortedByAge(ascending);

        verify(userService, times(1)).getUsersSortedByAge(ascending);
        assertEquals(users, result);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        UserDTO user = new UserDTO();

        when(userService.getUserById(userId)).thenReturn(user);

        UserDTO result = userController.getUserById(userId);

        verify(userService, times(1)).getUserById(userId);
        assertEquals(user, result);
    }
}