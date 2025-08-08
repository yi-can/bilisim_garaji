package com.example.springcase.controller;

import com.example.springcase.dto.ClassroomDto;
import com.example.springcase.dto.UserDto;
import com.example.springcase.model.Classroom;
import com.example.springcase.model.TeacherClassroomAssignment;
import com.example.springcase.model.User;
import com.example.springcase.request.TeacherClassroomAssignmentRequest;
import com.example.springcase.security.CustomUserDetails;
import com.example.springcase.service.ClassroomService;
import com.example.springcase.service.PermissionService;
import com.example.springcase.service.TeacherService;
import com.example.springcase.service.TeacherClassroomAssignmentService;
import com.example.springcase.service.UserService;
import com.example.springcase.model.enums.ResourceName;

import jakarta.validation.Valid;
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
    private final UserService userService;
    private final ClassroomService classroomService;
    private final TeacherService teacherService;  // Bunu ekledim, çünkü getMyClasses'da lazım

    @GetMapping
    public ResponseEntity<?> getAllAssignments(Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        if (!permissionService.hasPermission(user.getRole(), ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        List<TeacherClassroomAssignment> list = assignmentService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable UUID id, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        if (!permissionService.hasPermission(user.getRole(), ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        TeacherClassroomAssignment assignment = assignmentService.findById(id);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping
    public ResponseEntity<?> createAssignment(@Valid @RequestBody TeacherClassroomAssignmentRequest request,
                                              Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        if (!permissionService.hasPermission(user.getRole(), ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "create")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }

        try {
            User teacher = userService.findById(request.getTeacherId());
            Classroom classroom = classroomService.findById(request.getClassroomId());

            TeacherClassroomAssignment assignment = TeacherClassroomAssignment.builder()
                    .teacher(teacher)
                    .classroom(classroom)
                    .build();

            TeacherClassroomAssignment created = assignmentService.create(assignment);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Hata: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable UUID id, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        if (!permissionService.hasPermission(user.getRole(), ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "delete")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-classes")
    public ResponseEntity<?> getMyClasses(Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (!permissionService.hasPermission(user.getRole(), ResourceName.TEACHER_CLASSROOM_ASSIGNMENT, "read")) {
            return ResponseEntity.status(403).body("Yetkiniz yok");
        }

        List<ClassroomDto> classrooms = teacherService.getMyClassrooms(user.getId());
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/my-students")
    public ResponseEntity<?> getMyStudents(Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        List<UserDto> students = teacherService.getMyStudents(user.getId());
        return ResponseEntity.ok(students);
    }
}
