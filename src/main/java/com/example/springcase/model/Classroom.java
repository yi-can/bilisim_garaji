package com.example.springcase.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Öğretmen-sınıf atamaları (ilişki)
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherClassroomAssignment> teacherAssignments;
    
    // İstersen öğrenciler ve kurslar için yorum satırlarını açabilirsin
    // @ManyToMany(mappedBy = "classrooms")
    // private List<User> students;

    // @ManyToMany(mappedBy = "classrooms")
    // private List<Course> courses;
}
