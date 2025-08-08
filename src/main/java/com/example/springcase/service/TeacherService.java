package com.example.springcase.service;

import com.example.springcase.dto.ClassroomDto;
import com.example.springcase.dto.UserDto;
import com.example.springcase.model.Role;
import com.example.springcase.model.TeacherClassroomAssignment;
import com.example.springcase.model.User;
import com.example.springcase.repository.TeacherClassroomAssignmentRepository;
import com.example.springcase.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherClassroomAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    public List<ClassroomDto> getMyClassrooms(UUID teacherId) {
        List<TeacherClassroomAssignment> assignments = assignmentRepository.findByTeacherId(teacherId);
        return assignments.stream()
                .map(a -> new ClassroomDto(
                        a.getClassroom().getId(),
                        a.getClassroom().getName(),
                        a.getClassroom().getOrganization() != null ? a.getClassroom().getOrganization().getName() : null))
                .collect(Collectors.toList());
    }

    public List<UserDto> getMyStudents(UUID teacherId) {
        List<TeacherClassroomAssignment> assignments = assignmentRepository.findByTeacherId(teacherId);

        List<UUID> classroomIds = assignments.stream()
                .map(a -> a.getClassroom().getId())
                .collect(Collectors.toList());

        // Burada UserRepository'de bu methodun tanımlı olması gerekir
        List<User> students = userRepository.findByClassroomIdInAndRole(classroomIds, Role.STUDENT);

        return students.stream()
                .map(s -> new UserDto(s.getId(), s.getUsername(), s.getEmail()))
                .collect(Collectors.toList());
    }
}
