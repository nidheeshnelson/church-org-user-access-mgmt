package com.nidheeshnelson.user_access.service;

import com.nidheeshnelson.user_access.dto.FamilyDTO;
import com.nidheeshnelson.user_access.entity.Family;
import com.nidheeshnelson.user_access.entity.User;
import com.nidheeshnelson.user_access.repository.FamilyRepository;
import com.nidheeshnelson.user_access.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    // Get all families a user belongs to
    public List<FamilyDTO> getUserFamilies() {
        // In a real-world scenario, you'd get the currently authenticated user (e.g., via SecurityContext)
        Optional<User> user = userRepository.findById(1L); // Placeholder for getting logged-in user
        List<Family> families = user.map(User::getFamilies).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToFamilyDTO(families);
    }

    // Add a user to a family
    public void addUserToFamily(FamilyDTO familyDTO) {
        Optional<Family> family = familyRepository.findById(familyDTO.getId());
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        family.ifPresent(f -> {
            f.getMembers().add(user);
            familyRepository.save(f);
        });
    }

    // Remove a user from a family
    public void removeUserFromFamily(FamilyDTO familyDTO) {
        Optional<Family> family = familyRepository.findById(familyDTO.getId());
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        family.ifPresent(f -> {
            f.getMembers().remove(user);
            familyRepository.save(f);
        });
    }

    // Convert Family entities to FamilyDTOs
    private List<FamilyDTO> mapToFamilyDTO(List<Family> families) {
        // Convert each Family entity to a FamilyDTO
        return null; // Placeholder logic
    }
}
