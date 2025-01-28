package com.UserInfo.user.services;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.repository.UserRepository;
import com.UserInfo.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Logger instance

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
            if (response != null && response.getUsers() != null && !response.getUsers().isEmpty()) {
                for (UserDTO user : response.getUsers()) {
                    logger.debug("User ID: {}", user.getId()); // Debugging the ID
                    if (user.getId() == null) {
                        throw new IllegalArgumentException("User ID cannot be null");
                    }
                }
                userRepository.saveAll(response.getUsers());
                logger.info("Users successfully loaded and saved from the external API.");
            } else {
                logger.warn("No users found in the API response.");
            }
        } catch (Exception e) {
            logger.error("Error fetching users from external API: {}", e.getMessage(), e);
        }
    }

    public List<UserDTO> getAllUsers() {
        logger.info("Fetching all users from the database.");
        return userRepository.findAll();
    }

    public List<UserDTO> getUsersByRole(String role) {
        logger.info("Fetching users by role: {}", role);
        return userRepository.findByRole(role);
    }

    public List<UserDTO> getUsersSortedByAge(boolean ascending) {
        logger.info("Fetching users sorted by age in {} order.", ascending ? "ascending" : "descending");
        return ascending ? userRepository.findAllByOrderByAgeAsc() : userRepository.findAllByOrderByAgeDesc();
    }

    public UserDTO getUserById(Long id) {
        logger.info("Fetching user by ID: {}", id);
        return userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with ID: {}", id);
            return new RuntimeException("User not found");
        });
    }
}
