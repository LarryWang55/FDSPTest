package com.ford.code1.example1.vehicle.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePriceApi {
    @NotNull String MSRP;
    @NotNull String Savings;
    @NotNull String finalPrice;
}
