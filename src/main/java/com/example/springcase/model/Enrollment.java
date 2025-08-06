package com.example.springcase.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    private String id;

    private String studentId; // Referans olarak sadece ID tutuyoruz
    private String courseId;

    private LocalDateTime enrollmentDate;
}