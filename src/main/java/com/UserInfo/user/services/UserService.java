package com.UserInfo.user.services;

import com.UserInfo.user.entity.UserDTO;
import com.UserInfo.user.exception.CustomException;
import com.UserInfo.user.exception.ExternalAPIException;
import com.UserInfo.user.exception.ResourceNotFoundException;
import com.UserInfo.user.repository.UserRepository;
import com.UserInfo.user.response.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Service
@EnableCaching
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Logger instance
    private static final String CIRCUIT_BREAKER_NAME = "userServiceCircuitBreaker";
    private static final String REDIS_KEY_USERS = "users";

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final RedisService redisService;


    public UserService(UserRepository userRepository, RedisService redisService) {
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.restTemplate = new RestTemplate();
    }

    @Value("${service.url}")
    private String url;

    @Retry(name = "userServiceRetry")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackLoadUsersFromExternalAPI")
    public void loadUsersFromExternalAPI() {
        try {
            UserResponse response = restTemplate.getForObject(url, UserResponse.class);
            logger.info("Fetching users from external API...");
            if (response == null || response.getUsers() == null || response.getUsers().isEmpty()) {
                logger.warn("No users found in the API response.");
                throw new ExternalAPIException("No users found in the external API response.");
            }

            for (UserDTO user : response.getUsers()) {
                logger.debug("User ID: {}", user.getId());
                if (user.getId() == null) {
                    throw new IllegalArgumentException("User ID cannot be null");
                }
            }

            userRepository.saveAll(response.getUsers());
            redisService.set(REDIS_KEY_USERS, response.getUsers()); // Cache the users
            logger.info("Users successfully loaded and saved from the external API.");

        } catch (IllegalArgumentException e) {
            logger.error("Invalid data from external API: {}", e.getMessage(), e);
            throw new CustomException("Invalid data received: " + e.getMessage(), e);
        } catch (RestClientException e) {
            logger.error("Error calling external API: {}", e.getMessage(), e);
            throw new ExternalAPIException("Failed to fetch data from the external API.", e);
        } catch (DataAccessException e) {
            logger.error("Database error while saving users: {}", e.getMessage(), e);
            throw new CustomException("Database error while saving API data.", e);
        } catch (Exception e) {
            logger.error("Unexpected error in loadUsersFromExternalAPI: {}", e.getMessage(), e);
            throw new CustomException("An unexpected error occurred while fetching users", e);
        }
    }


    public void fallbackLoadUsersFromExternalAPI(Exception ex) {
        logger.warn("Fallback method triggered. Could not fetch users from API. Returning empty list.");
    }


    @Retry(name = "userServiceRetry")
    public List<UserDTO> getAllUsers() {

        List<UserDTO> cachedUsers = redisService.get(REDIS_KEY_USERS, List.class);
        if (cachedUsers != null) {
            logger.info("Returning users from cache.");
            return cachedUsers;
        }
        List<UserDTO> users = userRepository.findAll();
        redisService.set(REDIS_KEY_USERS, users);
        return users;
    }

    @Retry(name = "userServiceRetry")
    public List<UserDTO> getUsersByRole(String role) {
        String cacheKey = "users_role_" + role;
        List<UserDTO> cachedUsers = redisService.get(cacheKey, List.class);
        if (cachedUsers != null) {
            logger.info("Returning role-based users from cache.");
            return cachedUsers;
        }
        logger.info("Fetching users by role: {}", role);

        if (role == null || role.trim().isEmpty()) {
            throw new CustomException("Role cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        List<UserDTO> users = userRepository.findByRole(role);

        if (users.isEmpty()) {
            throw new CustomException("No users found with role: " + role, HttpStatus.NOT_FOUND);
        }
        redisService.set(cacheKey, users);
        return users;
    }

    @Retry(name = "userServiceRetry")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackUsersSortedByAge")
    public List<UserDTO> getUsersSortedByAge(boolean ascending) {
        try {
            String cacheKey = ascending ? "users_sorted_age_asc" : "users_sorted_age_desc";
            List<UserDTO> cachedUsers = redisService.get(cacheKey, List.class);
            if (cachedUsers != null) {
                logger.info("Returning sorted users from cache.");
                return cachedUsers;
            }
            logger.info("Fetching users sorted by age in {} order.", ascending ? "ascending" : "descending");
            List<UserDTO> users = ascending ? userRepository.findAllByOrderByAgeAsc() : userRepository.findAllByOrderByAgeDesc();

            if (users.isEmpty()) {
                throw new CustomException("No users found in the database", HttpStatus.NOT_FOUND);
            }
            redisService.set(cacheKey, users);
            return users;

        } catch (DataAccessException e) {
            logger.error("Database error while fetching users sorted by age", e);
            throw new CustomException("Database error occurred while fetching users", e);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching users sorted by age", e);
            throw new CustomException("An unexpected error occurred while fetching users !", e);
        }
    }


    public List<UserDTO> fallbackUsersSortedByAge(boolean ascending, Throwable ex) {
        logger.warn("Fallback method triggered. Could not fetch users sorted by age due to: {}", ex.getMessage(), ex);
        return List.of(); // Return an empty list as a fallback response
    }


    @Retry(name = "userServiceRetry")
    public UserDTO getUserById(Long id) {
        String cacheKey = "user_" + id;
        UserDTO cachedUser = redisService.get(cacheKey, UserDTO.class);
        if (cachedUser != null) {
            logger.info("Returning user from cache.");
            return cachedUser;
        }

        logger.info("Fetching user by ID: {}", id);
        UserDTO user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        redisService.set(cacheKey, user);
        return user;
    }


}
