package com.ford.code1.example1.vehicle.api;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VehicleResponse extends BaseBodyResponse<VehicleResponse.VehicleResponseResult> {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleResponseResult {
        VehicleApi vehicle;
    }

}
