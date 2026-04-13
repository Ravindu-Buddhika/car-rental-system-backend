package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.repository.FuelTypeRepository;
import org.example.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Override
    public List<LookupDTO> getAllFuelTypes() {
        return fuelTypeRepository.findAll().stream()
                .map(ft -> new LookupDTO(ft.getId(), ft.getTypeName()))
                .collect(Collectors.toList());
    }

    @Override
    public LookupDTO saveFuelType(String name) {
        return null;
    }
}
