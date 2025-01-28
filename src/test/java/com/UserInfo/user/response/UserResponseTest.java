package com.UserInfo.user.response;

import com.UserInfo.user.entity.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class UserResponseTest {
    @Test
    public void testUserResponse_Constructor() {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO());
        users.add(new UserDTO());

        UserResponse response = new UserResponse(users);
        response.setUsers(users);
        assertEquals(users, response.getUsers());
    }


}
