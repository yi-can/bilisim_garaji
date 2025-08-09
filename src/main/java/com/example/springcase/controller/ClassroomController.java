package com.example.springcase.controller;

import com.example.springcase.dto.ClassroomDto;
import com.example.springcase.model.Classroom;
import com.example.springcase.model.User;
import com.example.springcase.model.Role;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.ClassroomService;
import com.example.springcase.service.PermissionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllClassrooms(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.CLASSROOM, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        List<Classroom> list = classroomService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassroomById(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.CLASSROOM, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Classroom classroom = classroomService.findById(id);
        return ResponseEntity.ok(classroom);
    }

    @PostMapping
    public ResponseEntity<?> createClassroom(@RequestBody Classroom classroom, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.CLASSROOM, "create")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Classroom created = classroomService.create(classroom);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClassroom(@PathVariable UUID id, @RequestBody Classroom classroom, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.CLASSROOM, "update")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        Classroom updated = classroomService.update(id, classroom);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (!permissionService.hasPermission(role, ResourceName.CLASSROOM, "delete")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
