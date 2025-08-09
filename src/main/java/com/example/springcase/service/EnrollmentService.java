package com.example.springcase.service;

import org.springframework.stereotype.Service;

import com.example.springcase.model.Enrollment;
import com.example.springcase.repository.EnrollmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment findById(UUID id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment create(Enrollment enrollment) {
        enrollment.setEnrollmentDate(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment update(UUID id, Enrollment updatedEnrollment) {
        Enrollment enrollment = findById(id);
        enrollment.setStudent(updatedEnrollment.getStudent());
        enrollment.setCourse(updatedEnrollment.getCourse());
        // Tarih genellikle güncellenmez ama istersen ekle
        return enrollmentRepository.save(enrollment);
    }

    public void delete(UUID id) {
        Enrollment enrollment = findById(id);
        enrollmentRepository.delete(enrollment);
    }

    public List<Enrollment> findByStudentId(UUID studentId) {
    return enrollmentRepository.findByStudent_Id(studentId);
    }

    
}

