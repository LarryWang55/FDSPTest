package com.ford.code1.example1.vehicle.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleResponse {
    String status;
    String statusCode;
    String message;
}
