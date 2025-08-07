package com.example.springcase.service;

import com.example.springcase.model.Organization;
import com.example.springcase.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization update(UUID id, Organization organization) {
        Organization existing = findById(id);
        existing.setName(organization.getName());
        return organizationRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public Organization findById(UUID id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization bulunamadı: " + id));
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }
}
