package com.example.springcase.controller;

import com.example.springcase.model.Organization;
import com.example.springcase.model.User;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.OrganizationService;
import com.example.springcase.service.PermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllOrganizations(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.ORGANIZATION, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        List<Organization> list = organizationService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.ORGANIZATION, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Organization org = organizationService.findById(id);
        return ResponseEntity.ok(org);
    }

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody Organization organization, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.ORGANIZATION, "create")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Organization created = organizationService.create(organization);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable UUID id, @RequestBody Organization organization, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.ORGANIZATION, "update")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Organization updated = organizationService.update(id, organization);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.ORGANIZATION, "delete")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
