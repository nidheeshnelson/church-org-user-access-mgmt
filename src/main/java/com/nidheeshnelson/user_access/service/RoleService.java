package com.nidheeshnelson.user_access.service;

import com.nidheeshnelson.user_access.entity.Role;
import com.nidheeshnelson.user_access.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    // Add a new role
    public Role addRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    // Assign a role to a user
    public void assignRoleToUser(Long userId, Long roleId) {
        // Logic for assigning a role to a user
    }
}
