package com.UserInfo.user.controller;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @CrossOrigin("*")
    @GetMapping("/load")
    public List<UserDTO> loadUsers() {
        userService.loadUsersFromExternalAPI();
        return userService.getAllUsers();
//        return "Users loaded successfully!";
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/role/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/sort/age")
    public List<UserDTO> getUsersSortedByAge(@RequestParam boolean ascending) {
        return userService.getUsersSortedByAge(ascending);
    }


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
