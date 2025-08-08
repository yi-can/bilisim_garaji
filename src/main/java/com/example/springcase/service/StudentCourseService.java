package com.example.springcase.service;

import com.example.springcase.dto.CourseDto;
import com.example.springcase.model.Enrollment;
import com.example.springcase.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseService {

    private final EnrollmentRepository enrollmentRepository;

    public List<CourseDto> getCoursesByStudentId(UUID studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(e -> new CourseDto(
                    e.getCourse().getId()
                .collect(Collectors.toList());
    }
}