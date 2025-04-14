package service.strategy.pricing;

import model.constant.VehicleType;

import java.util.Date;
import java.time.*;

public class WeekdayPricingStrategy implements PricingStrategy {

    private static final double BASE_RATE = 10;

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        /*
         * Weekday pricing: In this model we will have a fixed fee for all
         * vehicle types which is 10 per hour.
         */
        Duration duration = Duration.between(entryTime.toInstant(), exitTime.toInstant());
        double durationMinutes = duration.toMinutes();
        long hours = (long) Math.ceil(durationMinutes/60.0); //since i need ceil as per the Q
        //i can use older mthod of millisxsecond based but that not thread safe and
        //can be so should be used in only legacy based env
        return hours*BASE_RATE;
    }
}
