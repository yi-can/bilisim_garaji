package com.example.springcase.service;

import com.example.springcase.dto.OrganizationDto;
import com.example.springcase.model.Brand;
import com.example.springcase.model.Organization;
import com.example.springcase.repository.BrandRepository;
import com.example.springcase.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl {

    private final OrganizationRepository organizationRepository;
    private final BrandRepository brandRepository;

    public OrganizationDto createOrganization(OrganizationDto dto) {
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Organization org = Organization.builder()
                .name(dto.getName())
                .brand(brand)
                .build();

        org = organizationRepository.save(org);

        return mapToDto(org);
    }

    public List<OrganizationDto> getAllOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private OrganizationDto mapToDto(Organization org) {
        return OrganizationDto.builder()
                .id(org.getId())
                .name(org.getName())
                .brandId(org.getBrand() != null ? org.getBrand().getId() : null)
                .brandName(org.getBrand() != null ? org.getBrand().getName() : null)
                .build();
    }
}
