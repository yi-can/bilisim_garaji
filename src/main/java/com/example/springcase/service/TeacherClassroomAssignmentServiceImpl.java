package com.example.springcase.service;

import com.example.springcase.model.TeacherClassroomAssignment;
import com.example.springcase.repository.TeacherClassroomAssignmentRepository;
import com.example.springcase.service.TeacherClassroomAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherClassroomAssignmentServiceImpl implements TeacherClassroomAssignmentService {

    private final TeacherClassroomAssignmentRepository assignmentRepository;

    @Override
    public TeacherClassroomAssignment create(TeacherClassroomAssignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public void delete(UUID id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public TeacherClassroomAssignment findById(UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment bulunamadı: " + id));
    }

    @Override
    public List<TeacherClassroomAssignment> findByTeacherId(UUID teacherId) {
        return assignmentRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<TeacherClassroomAssignment> findByClassroomId(UUID classroomId) {
        return assignmentRepository.findByClassroomId(classroomId);
    }

    @Override
    public List<TeacherClassroomAssignment> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}

