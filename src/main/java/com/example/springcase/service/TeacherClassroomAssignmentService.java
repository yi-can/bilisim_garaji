package com.example.springcase.service;

import com.example.springcase.model.TeacherClassroomAssignment;

import java.util.List;
import java.util.UUID;

public interface TeacherClassroomAssignmentService {
    TeacherClassroomAssignment create(TeacherClassroomAssignment assignment);
    void delete(UUID id);
    TeacherClassroomAssignment findById(UUID id);
    List<TeacherClassroomAssignment> findByTeacherId(UUID teacherId);
    List<TeacherClassroomAssignment> findByClassroomId(UUID classroomId);
    
    // Yeni eklenen metod:
    List<TeacherClassroomAssignment> findAll();
}
