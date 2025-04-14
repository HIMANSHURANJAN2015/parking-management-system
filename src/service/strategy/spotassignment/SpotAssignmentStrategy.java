package service.strategy.spotassignment;

import model.*;
import model.constant.*;
import java.util.Optional;


public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
