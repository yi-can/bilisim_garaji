package com.example.springcase.repository;

import com.example.springcase.model.Permission;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByRoleAndResourceName(Role role, ResourceName resourceName);
}
