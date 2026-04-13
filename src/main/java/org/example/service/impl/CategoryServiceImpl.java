package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<LookupDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(cat -> new LookupDTO(cat.getId(), cat.getCategoryName()))
                .collect(Collectors.toList());
    }

    @Override
    public LookupDTO saveCategory(String name) {
        return null;
    }
}
