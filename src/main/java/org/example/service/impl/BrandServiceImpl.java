package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.repository.BrandRepository;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<LookupDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brand -> new LookupDTO(brand.getId(), brand.getBrandName()))
                .collect(Collectors.toList());
    }

    @Override
    public LookupDTO saveBrand(String name) {
        return null;
    }
}
