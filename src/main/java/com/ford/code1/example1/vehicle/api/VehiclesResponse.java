package com.ford.code1.example1.vehicle.api;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class VehiclesResponse extends BaseBodyResponse<VehiclesResponse.VehiclesResponseResult> {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehiclesResponseResult {
        List<VehicleApi> vehicles;
    }
}
