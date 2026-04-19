package org.example.service;

import org.example.model.dto.request.LookupDTO;

import java.util.List;

public interface SeatingCapacityService {
    List<LookupDTO> getAllCapacities();
    LookupDTO saveCapacity(Integer capacity);
}
