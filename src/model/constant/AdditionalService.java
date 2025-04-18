package model.constant;

import java.util.List;

public enum AdditionalService {
    CAR_WASH(List.of(VehicleType.THREE_WHEELER, VehicleType.FOUR_WHEELER, VehicleType.THREE_WHEELER_EV, VehicleType.FOUR_WHEELER_EV), 200),

    BIKE_WASH(List.of(VehicleType.TWO_WHEELER, VehicleType.TWO_WHEELER_EV), 100),

    CAR_DETAILING(List.of(VehicleType.THREE_WHEELER, VehicleType.FOUR_WHEELER, VehicleType.THREE_WHEELER_EV, VehicleType.FOUR_WHEELER_EV), 500),
    EV_BIKE_CHARGING(List.of(VehicleType.TWO_WHEELER_EV), 100),
    EV_CAR_CHARGING(List.of(VehicleType.FOUR_WHEELER_EV), 200);

    AdditionalService(List<VehicleType> supportedVehicleTypes, double cost) {
        this.supportedVehicleTypes = supportedVehicleTypes;
        this.cost = cost;
    }

    private final List<VehicleType> supportedVehicleTypes;
    private final double cost;

    public List<VehicleType> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }

    public double getCost() {
        return cost;
    }
}
