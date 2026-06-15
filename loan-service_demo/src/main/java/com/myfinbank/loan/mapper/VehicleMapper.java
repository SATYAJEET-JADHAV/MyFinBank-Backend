package com.myfinbank.loan.mapper;

import com.myfinbank.loan.dto.VehicleRequest;
import com.myfinbank.loan.dto.VehicleResponse;
import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.enums.VehicleStatus;

public class VehicleMapper {

    private VehicleMapper() {
    }

    public static Vehicle toEntity(VehicleRequest request) {
        return Vehicle.builder()
                .make(request.getMake())
                .model(request.getModel())
                .variant(request.getVariant())
                .year(request.getYear())
                .vehicleType(request.getVehicleType())
                .exShowroomPrice(request.getExShowroomPrice())
                .onRoadPrice(request.getOnRoadPrice())
                .status(VehicleStatus.ACTIVE)
                .build();
    }

    public static VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .variant(vehicle.getVariant())
                .year(vehicle.getYear())
                .vehicleType(vehicle.getVehicleType())
                .exShowroomPrice(vehicle.getExShowroomPrice())
                .onRoadPrice(vehicle.getOnRoadPrice())
                .status(vehicle.getStatus())
                .build();
    }

    public static void updateEntity(Vehicle vehicle, VehicleRequest request) {
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setVariant(request.getVariant());
        vehicle.setYear(request.getYear());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setExShowroomPrice(request.getExShowroomPrice());
        vehicle.setOnRoadPrice(request.getOnRoadPrice());
    }
}