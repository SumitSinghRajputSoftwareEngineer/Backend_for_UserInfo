package com.UserInfo.user.response;

import com.UserInfo.user.entity.UserDTO;

import java.util.List;

public class UserResponse {
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
