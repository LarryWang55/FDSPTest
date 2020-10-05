package com.ford.code1.example1.vehicle2;

import com.ford.code1.example1.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Vehicle2Service {
    private Vehicle2Repository vehicle2Repository;

    @Autowired
    public Vehicle2Service(Vehicle2Repository vehicle2Repository) {
        this.vehicle2Repository = vehicle2Repository;
    }

    public Vehicle2 create(Vehicle2 newVehicle2) {
        return this.vehicle2Repository.save(newVehicle2);
    }

    public List<Vehicle2> getAllVehicle2s() {
        return this.vehicle2Repository.findAll();
    }

    public Optional<Vehicle2> getVehicle2(Long vehicleId) {
        return this.vehicle2Repository.findByVehicleId(vehicleId);
    }

}
