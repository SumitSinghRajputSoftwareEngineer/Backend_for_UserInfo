package com.UserInfo.user.repository;


import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindByRole() {
        // Given
        String role = "ADMIN";
        UserDTO user = new UserDTO();
        user.setRole(role);
        List<UserDTO> expectedUsers = Collections.singletonList(user);

        // When
        when(userRepository.findByRole(role)).thenReturn(expectedUsers);
        List<UserDTO> actualUsers = userRepository.findByRole(role);

        // Then
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testFindAllByOrderByAgeAsc() {
        // Given
        UserDTO user1 = new UserDTO();
        user1.setAge(30);
        UserDTO user2 = new UserDTO();
        user2.setAge(25);
        UserDTO user3 = new UserDTO();
        user3.setAge(35);
        List<UserDTO> expectedUsers = List.of(user2, user1, user3);

        // When
        when(userRepository.findAllByOrderByAgeAsc()).thenReturn(expectedUsers);
        List<UserDTO> actualUsers = userRepository.findAllByOrderByAgeAsc();

        // Then
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testFindAllByOrderByAgeDesc() {
        // Given
        UserDTO user1 = new UserDTO();
        user1.setAge(30);
        UserDTO user2 = new UserDTO();
        user2.setAge(25);
        UserDTO user3 = new UserDTO();
        user3.setAge(35);
        List<UserDTO> expectedUsers = List.of(user3, user1, user2);

        // When
        when(userRepository.findAllByOrderByAgeDesc()).thenReturn(expectedUsers);
        List<UserDTO> actualUsers = userRepository.findAllByOrderByAgeDesc();

        // Then
        assertEquals(expectedUsers, actualUsers);
    }
}