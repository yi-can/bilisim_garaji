package com.example.springcase.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrganizationDto {
    private UUID id;
    private String name;
    private UUID brandId;
    private String brandName; // opsiyonel
}
