package com.nidheeshnelson.user_access.service;

import com.nidheeshnelson.user_access.dto.UserResponseDTO;
import com.nidheeshnelson.user_access.entity.User;
import com.nidheeshnelson.user_access.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Get user profile by ID
    public UserResponseDTO getUserProfile() {
        // In a real-world scenario, you'd get the currently authenticated user (e.g., via SecurityContext)
        Optional<User> user = userRepository.findById(1L); // Placeholder for getting logged-in user
        return user.map(this::mapToDTO).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user profile
    public void updateUserProfile(UserResponseDTO userResponseDTO) {
        User user = userRepository.findById(userResponseDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userResponseDTO.getFirstName());
        user.setLastName(userResponseDTO.getLastName());
        user.setPhoneNumber(userResponseDTO.getPhoneNumber());
        user.setEmail(userResponseDTO.getEmail());

        userRepository.save(user);
    }

    // Convert User entity to DTO
    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
