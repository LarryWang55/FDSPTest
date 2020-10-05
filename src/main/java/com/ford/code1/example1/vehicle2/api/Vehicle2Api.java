package com.ford.code1.example1.vehicle2.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle2Api {
    String vehicleId;
    String make;
    String model;
    String modelYear;
    String bodyStyle;
    String engine;
    String drivetype;
    String color;
    String MPG;
    String ExteriorFeatures;
    String InteriorFeatures;
    String MSRP;
    String Savings;
    String finalPrice;
}
