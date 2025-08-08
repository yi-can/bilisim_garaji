package com.example.springcase.controller;

import com.example.springcase.dto.CourseDto;
import com.example.springcase.model.User;
import com.example.springcase.model.enums.ResourceName;
import com.example.springcase.service.PermissionService;
import com.example.springcase.service.StudentCourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentCourseService studentCourseService;
    private final PermissionService permissionService;

    @GetMapping("/my-courses")
    public ResponseEntity<?> getMyCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        List<CourseDto> courses = studentCourseService.getCoursesByStudentId(user.getId());
        return ResponseEntity.ok(courses);
    }
}
