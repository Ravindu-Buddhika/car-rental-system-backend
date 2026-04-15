package org.example.controller;

import org.example.model.dto.request.LookupDTO;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<LookupDTO> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public LookupDTO add(@RequestBody String name) {
        return categoryService.saveCategory(name);
    }
}
