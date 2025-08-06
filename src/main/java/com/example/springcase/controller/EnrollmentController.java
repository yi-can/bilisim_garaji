package com.example.springcase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springcase.model.Enrollment;
import com.example.springcase.service.EnrollmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@PreAuthorize("hasRole('SUPERADMIN')")  // Yalnızca SuperAdmin erişebilir
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(enrollmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.create(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable UUID id, @RequestBody Enrollment updatedEnrollment) {
        return ResponseEntity.ok(enrollmentService.update(id, updatedEnrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
