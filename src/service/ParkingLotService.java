package service;

import model.ParkingFloor;
import model.ParkingGate;
import model.ParkingLot;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import repository.ParkingLotRepository;
import util.ParkingLotUtils;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;
    private ParkingLotUtils parkingLotUtils;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotUtils parkingLotUtils) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotUtils = parkingLotUtils;
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
        this.parkingLotUtils.print(parkingLot);
    }
}