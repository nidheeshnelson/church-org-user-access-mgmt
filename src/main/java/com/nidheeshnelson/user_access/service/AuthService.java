package com.nidheeshnelson.user_access.service;

import com.nidheeshnelson.user_access.dto.LoginRequestDTO;
import com.nidheeshnelson.user_access.dto.RegisterRequestDTO;
import com.nidheeshnelson.user_access.entity.User;
import com.nidheeshnelson.user_access.repository.UserRepository;
import com.nidheeshnelson.user_access.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // Register a new user
    public void register(RegisterRequestDTO registerRequestDTO) {
        // Check if email or phone number already exists
        if (userRepository.existsByEmail(registerRequestDTO.getEmail()) || userRepository.existsByPhoneNumber(registerRequestDTO.getPhoneNumber())) {
            throw new RuntimeException("User with this email or phone number already exists");
        }

        User user = new User();
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setLastName(registerRequestDTO.getLastName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPhoneNumber(registerRequestDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userRepository.save(user);
    }

    // Authenticate user and generate token
    public String login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }
}
