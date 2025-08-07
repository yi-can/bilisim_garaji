package com.example.springcase.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.springcase.model.Course;
import com.example.springcase.model.User;
import com.example.springcase.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    // Constructor injection
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(UUID id, Course updatedCourse) {
        Course course = findById(id);
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setTeacher(updatedCourse.getTeacher());
        return courseRepository.save(course);
    }

    @Override
    public void delete(UUID id) {
        Course course = findById(id);
        courseRepository.delete(course);
    }

    @Override
    public List<Course> findCoursesByTeacher(User teacher) {
        return courseRepository.findByTeacherId(teacher.getId());
    }

    @Override
    public List<Course> findCoursesForStudent(User student) {
        return courseRepository.findByEnrolledStudents_Id(student.getId());
    }
}
