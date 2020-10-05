package com.ford.code1.example1.vehicle2;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface  Vehicle2Repository extends Repository<Vehicle2, Long> {
    List<Vehicle2> findAll();

    Vehicle2 save(Vehicle2 vehicle2);

    Optional<Vehicle2> findById(Long id);

    Optional<Vehicle2> findByVehicleId(Long vehicleId);

}
