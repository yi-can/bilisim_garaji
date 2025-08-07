package com.example.springcase.service;

import com.example.springcase.model.Permission;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.repository.PermissionRepository;
import com.example.springcase.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Rolleri oluştur
        Role superAdmin = createRoleIfNotExists("SUPERADMIN", 0);
        Role teacher = createRoleIfNotExists("TEACHER", 1);
        Role student = createRoleIfNotExists("STUDENT", 2);

        // SuperAdmin - Full Access
        createPermissionIfNotExists(superAdmin, ResourceName.COURSE, true, true, true, true);
        createPermissionIfNotExists(superAdmin, ResourceName.ENROLLMENT, true, true, true, true);
        createPermissionIfNotExists(superAdmin, ResourceName.USER, true, true, true, true);

        // Teacher - Read-only
        createPermissionIfNotExists(teacher, ResourceName.COURSE, false, true, false, false);
        createPermissionIfNotExists(teacher, ResourceName.ENROLLMENT, false, true, false, false);
        createPermissionIfNotExists(teacher, ResourceName.USER, false, true, false, false);

        // Student - Sadece ders görme izni
        createPermissionIfNotExists(student, ResourceName.COURSE, false, true, false, false);
    }

    private Role createRoleIfNotExists(String name, int profileId) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setName(name);
            role.setProfileId(profileId);
            return roleRepository.save(role);
        });
    }

    private void createPermissionIfNotExists(Role role, ResourceName resourceName,
                                             boolean canCreate, boolean canRead, boolean canUpdate, boolean canDelete) {
        if (permissionRepository.findByRoleAndResourceName(role, resourceName).isEmpty()) {
            Permission permission = new Permission();
            permission.setRole(role);
            permission.setCanCreate(canCreate);
            permission.setCanRead(canRead);
            permission.setCanUpdate(canUpdate);
            permission.setCanDelete(canDelete);
            permissionRepository.save(permission);
        }
    }
}