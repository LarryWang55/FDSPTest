package com.ford.code1.example1.vehicle2;

import com.ford.code1.example1.vehicle2.api.Vehicle2Api;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class Vehicle2Mapper {

    private static final Logger logger = Logger.getLogger(Vehicle2Mapper.class.getName());

    public Vehicle2 toVehicle2(Vehicle2Api vehicle2Api) {
        Vehicle2 vehicle2 = new Vehicle2();

        vehicle2.vehicleId = vehicle2Api.getVehicleId();

        vehicle2.make = vehicle2Api.getMake();
        vehicle2.model = vehicle2Api.getModel();
        vehicle2.modelYear = vehicle2Api.getModelYear();
        vehicle2.bodyStyle = vehicle2Api.getBodyStyle();
        vehicle2.engine = vehicle2Api.getEngine();
        vehicle2.driveType = vehicle2Api.getDrivetype();
        vehicle2.color = vehicle2Api.getColor();

        vehicle2.MPG = Integer.getInteger(vehicle2Api.getMPG());
        vehicle2.exteriorFeatures = vehicle2Api.getExteriorFeatures();
        vehicle2.interiorFeatures = vehicle2Api.getInteriorFeatures();

        String usCurrencyString;
        Float usCurrencyAmount;

        usCurrencyString = vehicle2Api.getMSRP();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString2(usCurrencyString);
            vehicle2.MSRP = usCurrencyAmount;
        }

        usCurrencyString = vehicle2Api.getSavings();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString2(usCurrencyString);
            vehicle2.savings = usCurrencyAmount;
        }

        usCurrencyString = vehicle2Api.getFinalPrice();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString2(usCurrencyString);
            vehicle2.finalPrice = usCurrencyAmount;
        }

        return vehicle2;
    }

    private Float parseUSDollarString2(String usCurrencyString) {
        logger.info("Input usCurrencyString = " + usCurrencyString);
        Float usCurrencyAmount = 0.0f;
        Locale locale = Locale.US;
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        try {
            usCurrencyAmount = format.parse(usCurrencyString).floatValue();
        } catch (ParseException ex) {
            logger.info("parseUSDollarring failed. Input string: " + usCurrencyString
                    + "; exception: " + ex);
            usCurrencyAmount = 0.0f;
        }
        return usCurrencyAmount;
    }

    public Vehicle2Api fromVehicle2(Vehicle2 vehicle2) {
        Vehicle2Api vehicle2Api = new Vehicle2Api();

        if (null == vehicle2) {
            return vehicle2Api;
        }

        vehicle2Api.setVehicleId(vehicle2.vehicleId.toString());

        vehicle2Api.setMake(vehicle2.make);
        vehicle2Api.setModel(vehicle2.model);
        vehicle2Api.setModelYear(vehicle2.modelYear);
        vehicle2Api.setBodyStyle(vehicle2.bodyStyle);
        vehicle2Api.setEngine(vehicle2.engine);
        vehicle2Api.setDrivetype(vehicle2.driveType);
        vehicle2Api.setColor(vehicle2.color);

        if (null != vehicle2.MPG) {
            vehicle2Api.setMPG(vehicle2.MPG.toString());
        }

        vehicle2Api.setExteriorFeatures(vehicle2.exteriorFeatures);
        vehicle2Api.setInteriorFeatures(vehicle2.interiorFeatures);

        vehicle2Api.setMSRP(vehicle2.MSRP.toString());
        vehicle2Api.setSavings(vehicle2.savings.toString());
        vehicle2Api.setFinalPrice(vehicle2.finalPrice.toString());

        return vehicle2Api;
    }

    public List<Vehicle2Api> fromVehicle2List(List<Vehicle2> vehicle2List) {
        return vehicle2List.stream().map(this::fromVehicle2).collect(Collectors.toList());
    }

}
