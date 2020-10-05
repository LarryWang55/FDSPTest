package com.ford.code1.example1.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(Vehicle newVehicle) {
        return this.vehicleRepository.save(newVehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    public List<Vehicle> getAllVehiclesByModelName(String modelName) {
        return this.vehicleRepository.findByModel(modelName);
    }

    public List<Vehicle> getAllVehiclesByPriceBetween(Float from, Float to) {
        return this.vehicleRepository.findByFinalPriceBetween(from, to);
    }

    public List<Vehicle> getAllVehiclesByExteriorOrInterior(String exterior, String interior) {
        return this.vehicleRepository.findByExteriorFeaturesContainingOrInteriorFeaturesContaining(exterior, interior);
    }

    public Optional<Vehicle> getVehicle(Long id) {
        return this.vehicleRepository.findById(id);
    }
}
