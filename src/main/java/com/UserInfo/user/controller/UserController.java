package com.UserInfo.user.controller;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "API for managing users")
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Load users from external API", description = "Fetches user data from an external API and loads it into the database.")
    @GetMapping("/load")
    public List<UserDTO> loadUsers() {
        userService.loadUsersFromExternalAPI();
        return userService.getAllUsers();
    }

    @Operation(summary = "Get users by role", description = "Fetches users based on their role.")
    @GetMapping("/role/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @Operation(summary = "Get users sorted by age", description = "Fetches users sorted by age in ascending or descending order.")
    @GetMapping("/sort/age")
    public List<UserDTO> getUsersSortedByAge(@RequestParam boolean ascending) {
        return userService.getUsersSortedByAge(ascending);
    }

    @Operation(summary = "Get user by ID", description = "Fetches a specific user by their ID.")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
