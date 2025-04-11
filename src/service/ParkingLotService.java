package service;

import model.ParkingFloor;
import model.ParkingGate;
import model.ParkingLot;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import repository.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot create(String parkingLotName, String address, List<ParkingFloor> parkingFloors,
                             List<ParkingGate> parkingGates) {
        //Validate inputs
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(parkingLotName);
        parkingLot.setAddress(address);
        parkingLot.setParkingFloors(parkingFloors);
        parkingLot.setParkingGates(parkingGates);
        return parkingLotRepository.save(parkingLot);
    }

    public void printParkingLot(long parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId);

    }
}