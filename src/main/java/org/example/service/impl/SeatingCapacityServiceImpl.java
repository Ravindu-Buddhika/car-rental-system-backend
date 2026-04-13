package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.repository.SeatingCapacityRepository;
import org.example.service.SeatingCapacityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SeatingCapacityServiceImpl implements SeatingCapacityService {

    @Autowired
    private SeatingCapacityRepository seatingRepository;

    @Override
    public List<LookupDTO> getAllCapacities() {
        return seatingRepository.findAll().stream()
                .map(sc -> new LookupDTO(sc.getId(), String.valueOf(sc.getCapacity())))
                .collect(Collectors.toList());
    }

    @Override
    public LookupDTO saveCapacity(Integer capacity) {
        return null;
    }
}
