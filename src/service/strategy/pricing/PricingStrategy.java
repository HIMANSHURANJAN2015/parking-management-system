package service.strategy.pricing;

import java.util.Date;
import model.constant.VehicleType;

public interface PricingStrategy {
    double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType);
}