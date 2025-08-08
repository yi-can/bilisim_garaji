package com.example.springcase.service;

import com.example.springcase.dto.BrandDto;
import com.example.springcase.model.Brand;
import com.example.springcase.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    // Otomatik kod üretimi: krm1, krm2, krm3 ...
    private String generateCode() {
        long count = brandRepository.count() + 1;
        return "krm" + count;
    }

    public BrandDto createBrand(BrandDto dto) {
        Brand brand = Brand.builder()
                .name(dto.getName())
                .code(generateCode())
                .build();
        brand = brandRepository.save(brand);
        return mapToDto(brand);
    }

    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BrandDto updateBrand(UUID id, BrandDto dto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        brand.setName(dto.getName());
        brand = brandRepository.save(brand);
        return mapToDto(brand);
    }

    public void deleteBrand(UUID id) {
        brandRepository.deleteById(id);
    }

    private BrandDto mapToDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .code(brand.getCode())
                .build();
    }
}
