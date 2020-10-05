package com.ford.code1.example1.vehicle2.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Vehicle2Response {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vehicle2ResponseResult {
        Vehicle2Api vehicle;
    }

}
