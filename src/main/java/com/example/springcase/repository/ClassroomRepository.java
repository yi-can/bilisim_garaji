package com.example.springcase.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcase.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    List<Classroom> findByOrganizationId(UUID organizationId);
    
}
