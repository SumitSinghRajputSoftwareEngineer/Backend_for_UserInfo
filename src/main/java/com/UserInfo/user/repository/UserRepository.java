package com.UserInfo.user.repository;

import com.UserInfo.user.entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDTO,Long> {
    List<UserDTO> findByRole(String role);

    List<UserDTO> findAllByOrderByAgeAsc();

    List<UserDTO> findAllByOrderByAgeDesc();
}
