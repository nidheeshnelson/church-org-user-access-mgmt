package com.nidheeshnelson.user_access.repository;

import com.nidheeshnelson.user_access.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    // Find a family by name
    Optional<Family> findByFamilyName(String familyName);

    // Find families that a specific user is a member of
    List<Family> findByMembers_Id(Long userId);
}
