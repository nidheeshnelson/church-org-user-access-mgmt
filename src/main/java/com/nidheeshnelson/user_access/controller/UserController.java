package com.nidheeshnelson.user_access.controller;

import com.nidheeshnelson.user_access.dto.UserResponseDTO;
import com.nidheeshnelson.user_access.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Endpoint to get the current user profile
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getUserProfile() {
        UserResponseDTO userProfile = userService.getUserProfile();
        return ResponseEntity.ok(userProfile);
    }

    // Endpoint to update the user profile
    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserResponseDTO userResponseDTO) {
        userService.updateUserProfile(userResponseDTO);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
