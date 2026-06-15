package com.myfinbank.auth.service;

import com.myfinbank.auth.dto.AuthResponse;
import com.myfinbank.auth.dto.LoginRequest;
import com.myfinbank.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}