package com.example.springcase.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcase.model.Organization;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}