package org.example.service;

import org.example.model.dto.request.LookupDTO;

import java.util.List;

public interface BrandService {
    List<LookupDTO> getAllBrands();
    LookupDTO saveBrand(String name);
}
