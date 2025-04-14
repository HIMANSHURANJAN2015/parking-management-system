package service.strategy.spotassignment;

import java.util.*;
import model.*;
import model.constant.*;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        //Counting parking spots for that vehicle in eah floor
        ParkingFloor selectedFloor = null;
        int spotsCountOnSelectedFloor = Integer.MAX_VALUE;
        for(ParkingFloor floor: parkingLot.getParkingFloors()) {
            if(floor.getFloorStatus() != ParkingFloorStatus.OPERATIONAL) {
                continue;
            }
            int count = 0;
            for(ParkingSpot spot: floor.getParkingSpots()) {
                if((spot.getVehicleType() == vehicleType) &&
                        (spot.getParkingSpotStatus() == ParkingSpotStatus.AVAILABLE)) {
                    count++;
                }
            }
            //if(count < spotsCountOnSelectedFloor) {
            if(count > 0 && count < spotsCountOnSelectedFloor) {
                spotsCountOnSelectedFloor = count;
                selectedFloor = floor;
            }
        }

        //Selcting nearest spot on the above selected floor
        // if((spotsCountOnSelectedFloor ==0) || (spotsCountOnSelectedFloor == Integer.MAX_VALUE)) {
        //     return Optional.empty();
        // }
        if(selectedFloor == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.getNearestSpotOnTheFloor(selectedFloor, vehicleType));
    }

    private ParkingSpot getNearestSpotOnTheFloor(ParkingFloor floor, VehicleType vehicleType) {
        for(ParkingSpot spot: floor.getParkingSpots()) {
            if((spot.getVehicleType() == vehicleType) && (spot.getParkingSpotStatus() == ParkingSpotStatus.AVAILABLE)) {
                //spot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED); //I forgot this and my test cases failed
                return spot;
            }
        }
        return null;
    }
}
