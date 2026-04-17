package org.example.service;

import org.example.model.dto.request.CarRequestDTO;
import org.example.model.dto.response.CarResponseDTO;
import java.util.List;

public interface CarService {
    CarResponseDTO saveCar(CarRequestDTO carDTO);
    List<CarResponseDTO> getAllCars();
    CarResponseDTO getCarById(Long id);
    CarResponseDTO updateCar(Long id, CarRequestDTO carDTO);
    void deleteCar(Long id);

    // Advance Filter Method
    List<CarResponseDTO> getFilteredCars(Long brandId, Long categoryId, Long fuelTypeId,
                                         Long transmissionId, Long capacityId, String status);

    List<CarResponseDTO> searchByModel(String model);
    void updateCarStatus(Long carId, String newStatus);
}