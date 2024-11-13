package com.practice.seventeen.service;

import com.practice.seventeen.dto.AuthRequest;
import com.practice.seventeen.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
}
