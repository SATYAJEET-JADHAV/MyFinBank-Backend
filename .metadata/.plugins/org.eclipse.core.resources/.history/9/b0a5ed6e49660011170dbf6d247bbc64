package com.myfinbank.auth.service;

import com.myfinbank.auth.dto.AuthResponse;
import com.myfinbank.auth.dto.LoginRequest;
import com.myfinbank.auth.dto.RegisterRequest;
import com.myfinbank.auth.entity.User;
import com.myfinbank.auth.exception.InvalidCredentialsException;
import com.myfinbank.auth.exception.UserAlreadyExistsException;
import com.myfinbank.auth.repository.UserRepository;
import com.myfinbank.auth.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(normalizeRole(request.getRole()));

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser);

        return new AuthResponse(
                token,
                savedUser.getUserId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException exception) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(
                token,
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

    private String normalizeRole(String role) {

        if (role == null || role.trim().isEmpty()) {
            return "CUSTOMER";
        }

        String normalizedRole = role.trim().toUpperCase();

        if (normalizedRole.startsWith("ROLE_")) {
            normalizedRole = normalizedRole.substring(5);
        }

        if (
                !normalizedRole.equals("CUSTOMER")
                        && !normalizedRole.equals("OFFICER")
                        && !normalizedRole.equals("MANAGER")
        ) {
            return "CUSTOMER";
        }

        return normalizedRole;
    }
}