package com.nidheeshnelson.user_access.repository;

import com.nidheeshnelson.user_access.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Check if a user exists by email or phone number
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
