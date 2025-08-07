package com.example.springcase.controller;

import com.example.springcase.model.Enrollment;
import com.example.springcase.model.User;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.EnrollmentService;
import com.example.springcase.service.PermissionService;
import org.springframework.security.access.AccessDeniedException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllEnrollments(Authentication authentication) {
            User user = (User) authentication.getPrincipal();

            if (!permissionService.hasPermission(user.getRole(), ResourceName.ENROLLMENT, "read")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu işlemi yapma yetkiniz yok");
        }

        List<Enrollment> enrollments;

        if (user.getRole().getName().equals("STUDENT")) {
            enrollments = enrollmentService.findByStudentId(user.getId());
        } else {
            enrollments = enrollmentService.findAll();
        }

        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.ENROLLMENT, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yetkisiz erişim");
        }

        Enrollment enrollment = enrollmentService.findById(id);

        // Kendi verisine erişim kontrolü
        if (user.getRole().getName().equals("STUDENT") && !enrollment.getStudent().getId().equals(user.getId())) {
            throw new AccessDeniedException("Bu kayda erişim yetkiniz yok.");
        }

        return ResponseEntity.ok(enrollment);
    }

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.ENROLLMENT, "create")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Kayıt oluşturma yetkiniz yok");
        }

        Enrollment created = enrollmentService.create(enrollment);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable UUID id, @RequestBody Enrollment updatedEnrollment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.ENROLLMENT, "update")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Kayıt güncelleme yetkiniz yok");
        }

        Enrollment updated = enrollmentService.update(id, updatedEnrollment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.ENROLLMENT, "delete")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Silme yetkiniz yok");
        }

        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}