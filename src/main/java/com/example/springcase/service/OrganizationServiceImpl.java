package com.example.springcase.service;

import com.example.springcase.dto.OrganizationDto;
import com.example.springcase.model.Organization;
import com.example.springcase.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private OrganizationDto mapToDto(Organization org) {
        return OrganizationDto.builder()
                .id(org.getId())
                .name(org.getName())
                .build();
    }

    private Organization mapToEntity(OrganizationDto dto) {
        return Organization.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto dto) {
        Organization organization = mapToEntity(dto);
        organization = organizationRepository.save(organization);
        return mapToDto(organization);
    }

    @Override
    public OrganizationDto updateOrganization(UUID id, OrganizationDto dto) {
        Organization existing = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found: " + id));
        existing.setName(dto.getName());
        existing = organizationRepository.save(existing);
        return mapToDto(existing);
    }

    @Override
    public void deleteOrganization(UUID id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public OrganizationDto findById(UUID id) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found: " + id));
        return mapToDto(org);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}