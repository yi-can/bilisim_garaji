package com.example.springcase.repository;

import com.example.springcase.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {


    List<Enrollment> findByStudent_Id(UUID studentId);
}
