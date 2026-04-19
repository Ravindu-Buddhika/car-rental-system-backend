package org.example.controller;

import org.example.model.dto.request.LookupDTO;
import org.example.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transmissions")
@CrossOrigin(origins = "http://localhost:5173")
public class TransmissionController {
    @Autowired
    private TransmissionService transmissionService;

    @GetMapping("/all")
    public List<LookupDTO> getAll() {
        return transmissionService.getAllTransmissions();
    }

    @PostMapping
    public LookupDTO add(@RequestBody String type) {
        return transmissionService.saveTransmission(type);
    }
}