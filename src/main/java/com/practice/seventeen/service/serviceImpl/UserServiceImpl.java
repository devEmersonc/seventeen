package com.practice.seventeen.service.serviceImpl;

import com.practice.seventeen.dto.RegisterUserDTO;
import com.practice.seventeen.dto.UserDTO;
import com.practice.seventeen.exception.UserNotFoundException;
import com.practice.seventeen.model.User;
import com.practice.seventeen.repository.RoleRepository;
import com.practice.seventeen.repository.UserRepository;
import com.practice.seventeen.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return this.convertEntityToDto(user);
    }

    @Override
    public void saveUser(RegisterUserDTO registerUserDTO) {
        User user = new User();
        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setEmail(registerUserDTO.getEmail());
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, RegisterUserDTO registerUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setEmail(registerUserDTO.getEmail());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDTO convertEntityToDto(User user) {
        UserDTO userdto = new UserDTO();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        return userdto;
    }
}
