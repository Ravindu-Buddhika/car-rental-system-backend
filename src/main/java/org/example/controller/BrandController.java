package org.example.controller;

import org.example.model.dto.request.LookupDTO;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@CrossOrigin(origins = "http://localhost:5173")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public List<LookupDTO> getAll() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public LookupDTO add(@RequestBody String name) {
        return brandService.saveBrand(name);
    }
}