package com.practice.seventeen.service;

import com.practice.seventeen.dto.RegisterUserDTO;
import com.practice.seventeen.dto.UserDTO;
import com.practice.seventeen.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
    UserDTO getUser(Long id);
    void saveUser(RegisterUserDTO registerUserDTO);
    void updateUser(Long id, RegisterUserDTO registerUserDTO);
    void deleteUser(Long id);
    UserDTO convertEntityToDto(User user);
}
