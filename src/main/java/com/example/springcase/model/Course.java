package com.example.springcase.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import java.util.List;

@Document(collection = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    private String id;

    @NotBlank
    @Size(max = 150)
    private String title;

    @Size(max = 1000)
    private String description;

    private String teacherId; // MongoDB'de ilişkisel @ManyToOne yok, öğretmen ID’si tutulur

    private List<String> enrollmentIds; // İlişkili Enrollment ID’leri listelenebilir
}
