package org.example.service;

import org.example.model.dto.request.LookupDTO;

import java.util.List;

public interface TransmissionService {
    List<LookupDTO> getAllTransmissions();
    LookupDTO saveTransmission(String type);
}
