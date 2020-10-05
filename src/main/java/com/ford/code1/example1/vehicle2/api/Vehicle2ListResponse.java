package com.ford.code1.example1.vehicle2.api;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class Vehicle2ListResponse extends BaseBodyResponse<Vehicle2ListResponse.Vehicle2ListResponseResult> {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vehicle2ListResponseResult {
        List<Vehicle2Api> vehicle2List;
    }

}
