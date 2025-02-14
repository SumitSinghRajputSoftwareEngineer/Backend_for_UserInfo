package com.UserInfo.user.controller;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.services.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //to load the users from external dataset to H2 DB as well to get all the users
    @GetMapping("/load")
    public List<UserDTO> loadUsers() {
        userService.loadUsersFromExternalAPI();
        return userService.getAllUsers();
    }

    //to get the user based on his role
    @GetMapping("/role/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable @NotBlank(message = "Role cannot be null or empty") String role) {
        return userService.getUsersByRole(role);
    }

    //to get the user after sorting their age
    @GetMapping("/sort/age")
    public List<UserDTO> getUsersSortedByAge(@RequestParam boolean ascending) {
        return userService.getUsersSortedByAge(ascending);
    }

    //to get the specific user
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
