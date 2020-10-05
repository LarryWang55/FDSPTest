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
public class VehicleFeatureApi {
    @NotNull List<String> Exterior;
    @NotNull List<String> Interior;
}
