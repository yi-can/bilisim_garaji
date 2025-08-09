package com.example.springcase.service;

import com.example.springcase.dto.OrganizationDto;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    OrganizationDto createOrganization(OrganizationDto dto);
    OrganizationDto updateOrganization(UUID id, OrganizationDto dto);
    void deleteOrganization(UUID id);
    OrganizationDto findById(UUID id);
    List<OrganizationDto> getAllOrganizations();
}
