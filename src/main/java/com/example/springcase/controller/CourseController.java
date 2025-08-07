package com.example.springcase.controller;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springcase.model.Course;
import com.example.springcase.model.User;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.CourseService;
import com.example.springcase.service.PermissionService;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<?> getAllCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu işlemi yapma yetkiniz yok");
        }
        
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yetkisiz erişim");
        }

        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "create")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ders oluşturma yetkiniz yok");
        }

        return ResponseEntity.ok(courseService.create(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable UUID id, @RequestBody Course updatedCourse, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "update")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ders güncelleme yetkiniz yok");
        }

        return ResponseEntity.ok(courseService.update(id, updatedCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable UUID id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "delete")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Silme yetkiniz yok");
        }

        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.COURSE, "read")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu işlemi yapma yetkiniz yok");
        }

        String roleName = user.getRole().getName();

        if (roleName.equalsIgnoreCase("TEACHER")) {
            return ResponseEntity.ok(courseService.findCoursesByTeacher(user));
        } else if (roleName.equalsIgnoreCase("STUDENT")) {
            return ResponseEntity.ok(courseService.findCoursesForStudent(user));
        } else {
            return ResponseEntity.ok(courseService.findAll());
        }
    }
}