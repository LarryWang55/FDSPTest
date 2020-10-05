package com.ford.code1.example1.vehicle;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends Repository<Vehicle, Long> {
    List<Vehicle> findAll();

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByFinalPriceBetween(Float from, Float to);

    List<Vehicle> findByExteriorFeaturesContainingOrInteriorFeaturesContaining(String exterior, String interior);

}
