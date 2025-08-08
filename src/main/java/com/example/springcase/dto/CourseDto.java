package com.example.springcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CourseDto {
    private UUID id;
    private String name;
    private String imageUrl;
}
