package com.ford.code1.example1.vehicle.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsApi {
    String make;
    String model;
    String modelYear;
    String bodyStyle;
    String engine;
    String drivetype;
    String color;
    @NotNull String MPG;
    VehicleFeatureApi vehicleFeature;
    List<VehiclePriceApi> vehiclePrice;
}
