package service.strategy.pricing;

import model.constant.VehicleType;
import repository.SlabRepository;
import model.*;

import java.util.Date;
import java.util.*;
import java.time.*;

public class WeekendPricingStrategy implements PricingStrategy{

    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> rateSlabs = this.slabRepository.getSortedSlabsByVehicleType(vehicleType);
        Duration duration = Duration.between(entryTime.toInstant(), exitTime.toInstant());
        double durationMinutes = duration.toMinutes();
        int durationHours = (int)(Math.ceil(durationMinutes/60.0));
        double cost = 0;
        for(Slab slab: rateSlabs) {
            int slabStart = slab.getStartHour();
            int slabEnd = slab.getEndHour();
            double slabPrice = slab.getPrice();
            if(slabEnd == -1) { //highest slab rate
                cost += (durationHours*slabPrice);
                break;
            }
            int slabWidth = slabEnd - slabStart;
            int durationInThisSlab = Math.min(slabWidth, durationHours);
            cost+= (durationInThisSlab*slabPrice);
            durationHours -= durationInThisSlab;
            if(durationHours <=0) {
                break;
            }
        }
        return cost;
    }
}
