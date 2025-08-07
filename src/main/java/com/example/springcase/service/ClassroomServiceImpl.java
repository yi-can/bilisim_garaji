package com.example.springcase.service;

import com.example.springcase.model.Classroom;
import com.example.springcase.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Override
    public Classroom create(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom update(UUID id, Classroom classroom) {
        Classroom existing = findById(id);
        existing.setName(classroom.getName());
        existing.setOrganization(classroom.getOrganization());
        return classroomRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        classroomRepository.deleteById(id);
    }

    @Override
    public Classroom findById(UUID id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom bulunamadı: " + id));
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public List<Classroom> findByOrganizationId(UUID organizationId) {
        return classroomRepository.findByOrganizationId(organizationId);
    }
}
