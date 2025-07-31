package com.nidheeshnelson.user_access.controller;

import com.nidheeshnelson.user_access.dto.FamilyDTO;
import com.nidheeshnelson.user_access.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    // Endpoint to get the list of families the user belongs to
    @GetMapping("/my-families")
    public ResponseEntity<List<FamilyDTO>> getUserFamilies() {
        List<FamilyDTO> families = familyService.getUserFamilies();
        return ResponseEntity.ok(families);
    }

    // Endpoint to add a user to a family
    @PostMapping("/add")
    public ResponseEntity<String> addUserToFamily(@RequestBody FamilyDTO familyDTO) {
        familyService.addUserToFamily(familyDTO);
        return ResponseEntity.ok("User added to family successfully");
    }

    // Endpoint to remove a user from a family
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeUserFromFamily(@RequestBody FamilyDTO familyDTO) {
        familyService.removeUserFromFamily(familyDTO);
        return ResponseEntity.ok("User removed from family successfully");
    }
}
