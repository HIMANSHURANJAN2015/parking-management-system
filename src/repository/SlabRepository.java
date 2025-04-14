package repository;

import model.*;
import model.constant.*;
import java.util.*;

public class SlabRepository {
    private Map<Long, Slab> slabMap = new HashMap<>();
    private long id = 1;

    public Slab save(Slab slab) {
        if(slab.getId() == 0) {
            slab.setId(id++);
        }
        slabMap.put(slab.getId(), slab);
        return slab;
    }

    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType) {
        List<Slab> slabs = new ArrayList<>();
        for(Long slabId: slabMap.keySet()) {
            Slab slab = slabMap.get(slabId);
            if(slab.getVehicleType()==vehicleType) {
                slabs.add(slab);
            }
        }
        //sorting it
        Collections.sort(slabs, (a,b)->{
            if(a.getStartHour()< b.getStartHour()) {
                //dont sort
                return -1;
            } else if(a.getStartHour()> b.getStartHour()) {
                //sort
                return 1;
            } else {
                return 0;
            }
        });
        return slabs;
        /*
         * return slabMap.values()
              .stream()
              .filter(slab -> slab.getSupportedVehicleTypes().contains(vehicleType)) // Filtering
              .sorted(Comparator.comparingInt(Slab::getStartHour))                 // Sorting
              .collect(Collectors.toList());

         */
    }
}
