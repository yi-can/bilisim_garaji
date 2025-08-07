package com.example.springcase.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Öğrenciler atanabilir (isteğe bağlı)
    // @ManyToMany(mappedBy = "classrooms")
    // private List<User> students;

    // Kurslar atanabilir (isteğe bağlı)
    // @ManyToMany(mappedBy = "classrooms")
    // private List<Course> courses;
}
