package com.ford.code1.example1.vehicle2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "vehicle_id")
    String vehicleId;

    @Column(name = "make")
    String make;

    @Column(name = "model")
    String model;

    @Column(name = "model_year")
    String modelYear;

    @Column(name = "body_style")
    String bodyStyle;

    @Column(name = "engine")
    String engine;

    @Column(name = "drive_type")
    String driveType;

    @Column(name = "color")
    String color;

    @Column(name = "mpg")
    Integer MPG;

    @Column(name = "exterior_features")
    String exteriorFeatures;

    @Column(name = "interior_features")
    String interiorFeatures;

    @Column(name = "msrp")
    Float MSRP;

    @Column(name = "savings")
    Float savings;

    @Column(name = "final_price")
    Float finalPrice;

}
