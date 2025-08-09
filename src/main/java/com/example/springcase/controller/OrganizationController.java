package com.example.springcase.controller;

import com.example.springcase.dto.OrganizationDto;
import com.example.springcase.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@Valid @RequestBody OrganizationDto dto) {
        OrganizationDto created = organizationService.createOrganization(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> list = organizationService.getAllOrganizations();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable UUID id) {
        OrganizationDto dto = organizationService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable UUID id, @Valid @RequestBody OrganizationDto dto) {
        OrganizationDto updated = organizationService.updateOrganization(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable UUID id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }
}

