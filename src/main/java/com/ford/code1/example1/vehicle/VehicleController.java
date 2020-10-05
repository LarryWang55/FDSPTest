package com.ford.code1.example1.vehicle;

import com.ford.code1.example1.vehicle.api.CreateVehicleRequest;
import com.ford.code1.example1.vehicle.api.CreateVehicleResponse;
import com.ford.code1.example1.vehicle.api.VehicleApi;
import com.ford.code1.example1.vehicle.api.VehiclesApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/vehicles")
public class VehicleController {
    VehicleService vehicleService;
    VehicleMapper vehicleMapper;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @ApiOperation(value = "Get Vehicles by feature", notes = "Return existing vehicles with matching feature")
    @GetMapping("/getVehicleByFeatures/{exterior}/{interior}")
    public ResponseEntity<VehiclesApi> getVehicleByFeature(@PathVariable String exterior,
                                                           @PathVariable String interior) {
        VehiclesApi vehiclesApi = new VehiclesApi();
        List<VehicleApi> vehicleApiList = new ArrayList<>();
        vehiclesApi.setVehicle(vehicleApiList);

        if (exterior == null || interior == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (exterior.isEmpty() || interior.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (exterior.length() < 3 || interior.length() < 3) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Vehicle> vehicleList = this.vehicleService.getAllVehiclesByExteriorOrInterior(exterior, interior);

        VehicleApi vehicleApi;
        for (Vehicle vehicle : vehicleList) {
            vehicleApi = this.vehicleMapper.fromVehicle(vehicle);
            vehicleApiList.add(vehicleApi);
        }

        return new ResponseEntity<>(vehiclesApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Vehicles by final price", notes = "Return existing vehicles within price range")
    @GetMapping("/getVehiclePrice/{from}/{to}")
    public ResponseEntity<VehiclesApi> getVehicleByPrice(@PathVariable Float from, @PathVariable Float to) {
        VehiclesApi vehiclesApi = new VehiclesApi();
        List<VehicleApi> vehicleApiList = new ArrayList<>();
        vehiclesApi.setVehicle(vehicleApiList);

        List<Vehicle> vehicleList = this.vehicleService.getAllVehiclesByPriceBetween(from, to);

        VehicleApi vehicleApi;
        for (Vehicle vehicle : vehicleList) {
            vehicleApi = this.vehicleMapper.fromVehicle(vehicle);
            vehicleApiList.add(vehicleApi);
        }

        return new ResponseEntity<>(vehiclesApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Vehicles by model", notes = "Return existing vehicles with matching model")
    @GetMapping("/getVehicleModelName/{modelName}")
    public ResponseEntity<VehiclesApi> getVehicleByModelName(@PathVariable String modelName) {
        VehiclesApi vehiclesApi = new VehiclesApi();
        List<VehicleApi> vehicleApiList = new ArrayList<>();
        vehiclesApi.setVehicle(vehicleApiList);

        List<Vehicle> vehicleList = this.vehicleService.getAllVehiclesByModelName(modelName);

        VehicleApi vehicleApi;
        for (Vehicle vehicle : vehicleList) {
            vehicleApi = this.vehicleMapper.fromVehicle(vehicle);
            vehicleApiList.add(vehicleApi);
        }

        return new ResponseEntity<>(vehiclesApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Vehicles", notes = "Return existing vehicles")
    @GetMapping
    public ResponseEntity<VehiclesApi> getVehicleInformation() {
        VehiclesApi vehiclesApi = new VehiclesApi();
        List<VehicleApi> vehicleApiList = new ArrayList<>();
        vehiclesApi.setVehicle(vehicleApiList);

        List<Vehicle> vehicleList = this.vehicleService.getAllVehicles();
        VehicleApi vehicleApi;
        for (Vehicle vehicle : vehicleList) {
            vehicleApi = this.vehicleMapper.fromVehicle(vehicle);
            vehicleApiList.add(vehicleApi);
        }

        return new ResponseEntity<>(vehiclesApi, HttpStatus.OK);
    }

    @ApiOperation(value = "Submit Vehicle", notes = "Submit vehicles based on the given input")
    @PostMapping("/vehicleInfomation/submitVehicle")
    public ResponseEntity<CreateVehicleResponse> submitVehicle(
            @Valid @RequestBody CreateVehicleRequest createVehicleRequest) {

        List<Vehicle> newVehicleList = this.vehicleMapper.toVehicleList(createVehicleRequest);
        int index = 0;
        int lastIndex = newVehicleList.size() - 1;
        StringBuilder sbIDs = new StringBuilder();
        for (Vehicle newVehicle : newVehicleList) {
            Vehicle vehicle = this.vehicleService.create(newVehicle);
            sbIDs.append(vehicle.getVehicleId());
            if (index < lastIndex) {
                sbIDs.append(",");
            }
        }

        CreateVehicleResponse createVehicleResponse =
                CreateVehicleResponse.builder()
                        .status("OK")
                        .statusCode("200")
                        .message(sbIDs.toString() + " submitted to database successfully")
                        .build();

        return new ResponseEntity<>(createVehicleResponse, HttpStatus.CREATED);
    }

}
