package com.example.springcase.service;

import java.util.List;
import java.util.UUID;

import com.example.springcase.model.Course;
import com.example.springcase.model.User;

public interface CourseService {

    List<Course> findAll();

    Course findById(UUID id);

    Course create(Course course);

    Course update(UUID id, Course updatedCourse);

    void delete(UUID id);

    List<Course> findCoursesByTeacher(User teacher);

    List<Course> findCoursesForStudent(User student);
}
