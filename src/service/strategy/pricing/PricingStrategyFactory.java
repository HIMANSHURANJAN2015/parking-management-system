package service.strategy.pricing;

import repository.SlabRepository;

import java.util.Calendar;
import java.util.Date;

public class PricingStrategyFactory {
    private SlabRepository slabRepository;

    public PricingStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    public PricingStrategy getPricingStrategy(Date exitTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitTime);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return new WeekendPricingStrategy(slabRepository);
        } else {
            return new WeekdayPricingStrategy();
        }
    }
}
