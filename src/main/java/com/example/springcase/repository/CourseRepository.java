package com.example.springcase.repository;

import com.example.springcase.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByTeacherId(UUID teacherId);
    List<Course> findByEnrolledStudents_Id(UUID studentId);
}