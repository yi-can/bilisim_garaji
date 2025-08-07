package com.example.springcase.service;

import com.example.springcase.model.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    Organization create(Organization organization);
    Organization update(UUID id, Organization organization);
    void delete(UUID id);
    Organization findById(UUID id);
    List<Organization> findAll();
}
