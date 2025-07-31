package com.nidheeshnelson.user_access.repository;

import com.nidheeshnelson.user_access.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find a role by name (e.g., ADMIN, MEMBER)
    Optional<Role> findByName(String name);
}
