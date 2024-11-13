package com.practice.seventeen.controller;

import com.practice.seventeen.dto.RegisterUserDTO;
import com.practice.seventeen.dto.UserDTO;
import com.practice.seventeen.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/save-user")
    public ResponseEntity<String> saveUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        userService.saveUser(registerUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody RegisterUserDTO registerUserDTO, @PathVariable Long id) {
        userService.updateUser(id, registerUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario actualizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado.");
    }
}
