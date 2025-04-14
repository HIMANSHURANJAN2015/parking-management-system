package service;

import model.ParkingFloor;
import model.ParkingSpot;
import model.constant.ParkingFloorStatus;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import repository.ParkingFloorRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloorService {

    private ParkingFloorRepository parkingFloorRepository;

    public ParkingFloorService(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    public ParkingFloor create(int floorNumber,
                               List<ParkingSpot> parkingSpots ){
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorNumber(floorNumber);
        parkingFloor.setParkingSpots(parkingSpots);
        parkingFloor.setFloorStatus(ParkingFloorStatus.OPERATIONAL);
        return parkingFloorRepository.save(parkingFloor);
    }
}
// floor 1
// 5 spots

// 101, 102, 103, 104, 105