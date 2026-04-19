package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.model.entity.Brand;
import org.example.repository.BrandRepository;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
        Brand brand = new Brand();
        brand.setBrandName(name);
        Brand saved = brandRepository.save(brand);
        return new LookupDTO(saved.getId(), saved.getBrandName());
    }
}
