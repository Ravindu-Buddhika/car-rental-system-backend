package org.example.service;

import org.example.model.dto.request.LookupDTO;

import java.util.List;

public interface FuelTypeService {
    public List<LookupDTO> getAllFuelTypes();
    public LookupDTO saveFuelType(String name);
}
