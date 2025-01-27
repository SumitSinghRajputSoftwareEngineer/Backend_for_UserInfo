package com.UserInfo.user.services;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.repository.UserRepository;
import com.UserInfo.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.restTemplate = new RestTemplate();
    }

    @Value("${service.url}")
    private String url;
    public void loadUsersFromExternalAPI() {

        try {
            UserResponse response = restTemplate.getForObject(url, UserResponse.class);
            if (response.getUsers() != null && !response.getUsers().isEmpty()) {
                for (UserDTO user : response.getUsers()) {
                    System.out.println("User ID: " + user.getId()); // Debugging the ID
                    if (user.getId() == null) {
                        throw new IllegalArgumentException("User ID cannot be null");
                    }
                }
                userRepository.saveAll(response.getUsers());
            } else {
                System.out.println("No users found in API response.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching users from external API: " + e.getMessage());
        }
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDTO> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public List<UserDTO> getUsersSortedByAge(boolean ascending) {
        return ascending ? userRepository.findAllByOrderByAgeAsc() : userRepository.findAllByOrderByAgeDesc();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
