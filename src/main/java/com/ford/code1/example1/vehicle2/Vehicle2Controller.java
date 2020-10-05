package com.ford.code1.example1.vehicle2;

import com.ford.code1.example1.vehicle2.api.Vehicle2ListResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/vehicle2s")
public class Vehicle2Controller {

    Vehicle2Service vehicle2Service;
    Vehicle2Mapper vehicle2Mapper;

    @Autowired
    public Vehicle2Controller(Vehicle2Service vehicle2Service, Vehicle2Mapper vehicle2Mapper) {
        this.vehicle2Service = vehicle2Service;
        this.vehicle2Mapper = vehicle2Mapper;
    }

    @ApiOperation(value = "Get All Vehicle2s", notes = "Returns all vehicle2s")
    @GetMapping
    public Vehicle2ListResponse list() {
        List<Vehicle2> vehicle2List = this.vehicle2Service.getAllVehicle2s();

        Vehicle2ListResponse.Vehicle2ListResponseResult result =
                Vehicle2ListResponse.Vehicle2ListResponseResult.builder()
                .vehicle2List(this.vehicle2Mapper.fromVehicle2List(vehicle2List))
                .build();

        return Vehicle2ListResponse.result(result, Vehicle2ListResponse.class);
    }

}
