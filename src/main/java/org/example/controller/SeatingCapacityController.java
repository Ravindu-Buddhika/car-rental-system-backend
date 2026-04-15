package org.example.controller;

import org.example.model.dto.request.LookupDTO;
import org.example.service.SeatingCapacityService; // ඔයාගේ interface නම
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/capacities")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatingCapacityController {
    @Autowired
    private SeatingCapacityService capacityService;

    @GetMapping("/all")
    public List<LookupDTO> getAll() {
        return capacityService.getAllCapacities();
    }

    @PostMapping
    public LookupDTO add(@RequestBody Integer capacity) {
        return capacityService.saveCapacity(capacity);
    }
}