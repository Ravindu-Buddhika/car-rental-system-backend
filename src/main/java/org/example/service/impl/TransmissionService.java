package org.example.service.impl;

import org.example.model.dto.request.LookupDTO;
import org.example.model.entity.Transmission;
import org.example.repository.TransmissionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TransmissionService implements org.example.service.TransmissionService {

    private TransmissionRepository transmissionRepository;
    @Override
    public List<LookupDTO> getAllTransmissions() {
        return transmissionRepository.findAll().stream()
                .map(t -> new LookupDTO(t.getId(), t.getTransmissionType()))
                .collect(Collectors.toList());
    }

    @Override
    public LookupDTO saveTransmission(String type) {
        Transmission t = new Transmission();
        t.setTransmissionType(type);
        Transmission saved = transmissionRepository.save(t);
        return new LookupDTO(saved.getId(), saved.getTransmissionType());
    }
}
