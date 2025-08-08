package com.example.springcase.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BrandDto {
    private UUID id;
    private String name;
    private String code;
}
