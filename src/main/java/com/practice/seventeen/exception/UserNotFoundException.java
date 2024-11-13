package com.practice.seventeen.exception;

public class UserNotFoundException extends ResourceNotFoundException{

    public UserNotFoundException() {
        super("Usuario no encontrado.");
    }
}
