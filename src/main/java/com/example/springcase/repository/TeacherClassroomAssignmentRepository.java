package com.example.springcase.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcase.model.TeacherClassroomAssignment;

import java.util.List;
import java.util.UUID;

public interface TeacherClassroomAssignmentRepository extends JpaRepository<TeacherClassroomAssignment, UUID> {
    List<TeacherClassroomAssignment> findByTeacherId(UUID teacherId);
    List<TeacherClassroomAssignment> findByClassroomId(UUID classroomId);
}
