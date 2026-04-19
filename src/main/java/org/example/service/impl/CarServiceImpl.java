package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.CarRequestDTO;
import org.example.model.dto.response.CarResponseDTO;
import org.example.model.entity.*;
import org.example.repository.*;
import org.example.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final TransmissionRepository transmissionRepository;
    private final SeatingCapacityRepository seatingRepository;

    @Override
    public CarResponseDTO saveCar(CarRequestDTO dto) {
        CarDetails car = new CarDetails();
        return mapToResponseDTO(carRepository.save(mapToEntity(car, dto)));
    }

    @Override
    public List<CarResponseDTO> getAllCars() {
        return carRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public CarResponseDTO getCarById(Long id) {
        return mapToResponseDTO(carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found!")));
    }

    @Override
    public CarResponseDTO updateCar(Long id, CarRequestDTO dto) {
        CarDetails car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found!"));
        return mapToResponseDTO(carRepository.save(mapToEntity(car, dto)));
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarResponseDTO> getFilteredCars(Long brandId, Long categoryId, Long fuelTypeId, Long transmissionId, Long capacityId, String status) {
        return carRepository.findCarsByFilters(brandId, categoryId, fuelTypeId, transmissionId, capacityId, status)
                .stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<CarResponseDTO> searchByModel(String model) {
        return carRepository.findByCarModelContainingIgnoreCase(model)
                .stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    private CarDetails mapToEntity(CarDetails car, CarRequestDTO dto) {
        car.setCarModel(dto.getCarModel());
        car.setPlateNumber(dto.getPlateNumber());
        car.setDailyRate(dto.getDailyRate());
        car.setStatus(dto.getStatus());
        car.setYear(dto.getYear());
        car.setDescription(dto.getDescription());
        car.setImageUrl(dto.getImageUrl());
        car.setCurrentLat(dto.getCurrentLat());
        car.setCurrentLng(dto.getCurrentLng());

        car.setBrand(brandRepository.findById(dto.getBrandId()).orElse(null));
        car.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        car.setFuelType(fuelTypeRepository.findById(dto.getFuelTypeId()).orElse(null));
        car.setTransmission(transmissionRepository.findById(dto.getTransmissionId()).orElse(null));
        car.setSeatingCapacity(seatingRepository.findById(dto.getSeatingCapacityId()).orElse(null));
        return car;
    }

    private CarResponseDTO mapToResponseDTO(CarDetails car) {
        CarResponseDTO resp = new CarResponseDTO();
        resp.setCarId(car.getCarId());
        resp.setCarModel(car.getCarModel());
        resp.setPlateNumber(car.getPlateNumber());
        resp.setDailyRate(car.getDailyRate());
        resp.setStatus(car.getStatus());
        resp.setYear(car.getYear());
        resp.setDescription(car.getDescription());
        resp.setImageUrl(car.getImageUrl());
        resp.setCurrentLat(car.getCurrentLat());
        resp.setCurrentLng(car.getCurrentLng());

        if (car.getBrand() != null) resp.setBrandName(car.getBrand().getBrandName());
        if (car.getCategory() != null) resp.setCategoryName(car.getCategory().getCategoryName());
        if (car.getFuelType() != null) resp.setFuelTypeName(car.getFuelType().getTypeName());
        if (car.getTransmission() != null) resp.setTransmissionType(car.getTransmission().getTransmissionType());
        if (car.getSeatingCapacity() != null) resp.setSeatingCapacity(car.getSeatingCapacity().getCapacity());
        return resp;
    }

    public void updateCarStatus(Long carId, String newStatus) {
        CarDetails car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

        car.setStatus(newStatus);
        carRepository.save(car);
    }
}