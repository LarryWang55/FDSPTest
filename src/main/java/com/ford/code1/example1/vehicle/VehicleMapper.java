package com.ford.code1.example1.vehicle;

import com.ford.code1.example1.vehicle.api.*;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;

@Component
public class VehicleMapper {

    private static final Logger logger = Logger.getLogger(VehicleMapper.class.getName());

    public List<Vehicle> toVehicleList(CreateVehicleRequest createVehicleRequest) {
        List<Vehicle> vehicleList = new ArrayList<>();
        VehiclesApi vehiclesApi = createVehicleRequest.getVehicles();
        List<VehicleApi> vehicleApiList = vehiclesApi.getVehicle();
        Vehicle vehicle;
        for (VehicleApi vehicleApi : vehicleApiList) {
            vehicle = toVehicle(vehicleApi);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public Vehicle toVehicle(VehicleApi vehicleApi) {
        Vehicle vehicle = new Vehicle();

        vehicle.vehicleId = Long.parseLong(vehicleApi.getVehicleId());

        VehicleDetailsApi vehicleDetailsApi = vehicleApi.getVehicleDetails();
        vehicle.make = vehicleDetailsApi.getMake();
        vehicle.model = vehicleDetailsApi.getModel();
        vehicle.modelYear = vehicleDetailsApi.getModelYear();
        vehicle.bodyStyle = vehicleDetailsApi.getBodyStyle();
        vehicle.engine = vehicleDetailsApi.getEngine();
        vehicle.driveType = vehicleDetailsApi.getDrivetype();
        vehicle.color = vehicleDetailsApi.getColor();

        vehicle.MPG = Integer.getInteger(vehicleDetailsApi.getMPG());

        VehicleFeatureApi vehicleFeatureApi = vehicleDetailsApi.getVehicleFeature();
        List<String> exterior = vehicleFeatureApi.getExterior();
        if (null != exterior) {
            vehicle.exteriorFeatures = castFeatureStrings(exterior);
        }

        List<String> interior = vehicleFeatureApi.getInterior();
        if (null != interior) {
            vehicle.interiorFeatures = castFeatureStrings(interior);
        }

        List<VehiclePriceApi> vehiclePriceApiList = vehicleDetailsApi.getVehiclePrice();
        VehiclePriceApi vehiclePriceApi = vehiclePriceApiList.get(0);

        String usCurrencyString;
        Float usCurrencyAmount;

        usCurrencyString = vehiclePriceApi.getMSRP();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString(usCurrencyString);
            vehicle.MSRP = usCurrencyAmount;
        }

        usCurrencyString = vehiclePriceApi.getSavings();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString(usCurrencyString);
            vehicle.savings = usCurrencyAmount;
        }

        usCurrencyString = vehiclePriceApi.getFinalPrice();
        if (null != usCurrencyString) {
            usCurrencyAmount = parseUSDollarString(usCurrencyString);
            vehicle.finalPrice = usCurrencyAmount;
        }

        return vehicle;
    }

    private Float parseUSDollarString(String usCurrencyString) {
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

    private String castFeatureStrings(List<String> featureStringList) {
        int lastIndex = featureStringList.size() - 1;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (String featureString: featureStringList) {
            sb.append(featureString);
            if (index < lastIndex) {
                sb.append(";");
            }
            index++;
        }
        return sb.toString();
    }

    public VehicleApi fromVehicle(Vehicle vehicle) {
        VehicleApi vehicleApi = new VehicleApi();

        if (null == vehicle) {
            return vehicleApi;
        }

        vehicleApi.setVehicleId(vehicle.vehicleId.toString());

        VehicleDetailsApi vehicleDetailsApi = new VehicleDetailsApi();
        vehicleApi.setVehicleDetails(vehicleDetailsApi);
        vehicleDetailsApi.setMake(vehicle.make);
        vehicleDetailsApi.setModel(vehicle.model);
        vehicleDetailsApi.setModelYear(vehicle.modelYear);
        vehicleDetailsApi.setBodyStyle(vehicle.bodyStyle);
        vehicleDetailsApi.setEngine(vehicle.engine);
        vehicleDetailsApi.setDrivetype(vehicle.driveType);
        vehicleDetailsApi.setColor(vehicle.color);

        if (null != vehicle.MPG) {
            vehicleDetailsApi.setMPG(vehicle.MPG.toString());
        }

        VehicleFeatureApi vehicleFeatureApi = new VehicleFeatureApi();
        vehicleDetailsApi.setVehicleFeature(vehicleFeatureApi);

        List<String> exteriorFeatureList = new ArrayList<>();
        vehicleFeatureApi.setExterior(exteriorFeatureList);

        if (null != vehicle.exteriorFeatures) {
            String[] exteriorFeatureArray = vehicle.exteriorFeatures.split(";");
            exteriorFeatureList.addAll(Arrays.asList(exteriorFeatureArray));
        }

        List<String> interiorFeatureList = new ArrayList<>();
        vehicleFeatureApi.setInterior(interiorFeatureList);

        if (null != vehicle.interiorFeatures) {
            String[] interiorFeatureArray = vehicle.interiorFeatures.split(";");
            interiorFeatureList.addAll(Arrays.asList(interiorFeatureArray));
        }

        VehiclePriceApi vehiclePriceApi = new VehiclePriceApi();
        List<VehiclePriceApi> vehiclePriceApiList = new ArrayList<>();
        vehiclePriceApiList.add(vehiclePriceApi);
        vehicleDetailsApi.setVehiclePrice(vehiclePriceApiList);

        if (null != vehicle.MSRP) {
            vehiclePriceApi.setMSRP(vehicle.MSRP.toString());
        }

        if (null != vehicle.savings) {
            vehiclePriceApi.setSavings(vehicle.savings.toString());
        }

        if (null != vehicle.finalPrice) {
            vehiclePriceApi.setFinalPrice(vehicle.finalPrice.toString());
        }

        return vehicleApi;
    }
}
