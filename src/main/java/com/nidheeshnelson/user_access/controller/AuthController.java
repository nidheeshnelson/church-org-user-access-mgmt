package com.nidheeshnelson.user_access.controller;

import com.nidheeshnelson.user_access.dto.LoginRequestDTO;
import com.nidheeshnelson.user_access.dto.RegisterRequestDTO;
import com.nidheeshnelson.user_access.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        authService.register(registerRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.login(loginRequestDTO);
        return ResponseEntity.ok("Bearer " + token);
    }
}
