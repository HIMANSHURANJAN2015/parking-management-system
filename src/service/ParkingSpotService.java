package service;

import model.ParkingSpot;
import model.constant.ParkingSpotStatus;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import repository.ParkingSpotRepository;

public class ParkingSpotService {

    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }
    // we send all params to service, and create the object in service
    // as the object creation might require some business logic, and all logics
    // should be inside service class
    public ParkingSpot create(int slotNumber,
                              ParkingSpotType parkingSpotType,
                              VehicleType vehicleType){
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setParkingSpotNumber(slotNumber);
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpot.setParkingSpotType(parkingSpotType);
        parkingSpot.setVehicleType(vehicleType);
        return parkingSpotRepository.save(parkingSpot);
    }
}