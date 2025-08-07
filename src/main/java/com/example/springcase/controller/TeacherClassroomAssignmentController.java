package com.example.springcase.controller;

import com.example.springcase.model.TeacherClassroomAssignment;
import com.example.springcase.model.User;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.TeacherClassroomAssignmentService;
import com.example.springcase.service.PermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teacher-classroom-assignments")
@RequiredArgsConstructor
public class TeacherClassroomAssignmentController {

    private final TeacherClassroomAssignmentService assignmentService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllAssignments(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        List<TeacherClassroomAssignment> list = assignmentService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        TeacherClassroomAssignment assignment = assignmentService.findById(id);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping
    public ResponseEntity<?> createAssignment(@RequestBody TeacherClassroomAssignment assignment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "create")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        TeacherClassroomAssignment created = assignmentService.create(assignment);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "delete")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
