package org.example.service;

import org.example.model.dto.request.LookupDTO;

import java.util.List;

public interface CategoryService {
    public List<LookupDTO> getAllCategories();
    public LookupDTO saveCategory(String name);
}
