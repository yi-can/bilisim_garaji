package com.example.springcase.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
@Builder  
public class ClassroomDto {
    private UUID id;
    private String name;
    private String organizationName;
}
