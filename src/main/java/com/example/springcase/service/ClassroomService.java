package com.example.springcase.service;

import com.example.springcase.model.Classroom;

import java.util.List;
import java.util.UUID;

public interface ClassroomService {
    Classroom create(Classroom classroom);
    Classroom update(UUID id, Classroom classroom);
    void delete(UUID id);
    Classroom findById(UUID id);
    List<Classroom> findAll();
    List<Classroom> findByOrganizationId(UUID organizationId);
}
