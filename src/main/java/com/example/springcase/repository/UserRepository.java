package com.example.springcase.repository;

import com.example.springcase.model.Role;
import com.example.springcase.model.User;
import com.example.springcase.model.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.classroom.id IN :classroomIds AND u.role = :role")
    List<User> findByClassroomIdAndRole(@Param("classroomIds") List<UUID> classroomIds, @Param("role") Role role);

    List<User> findByClassroomIdInAndRole(List<UUID> classroomIds, role role);

}
