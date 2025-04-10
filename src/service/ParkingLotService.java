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
//    private ParkingFloorService parkingFloorService;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
//        this.parkingFloorService = parkingFloorService;
    }

//    public ParkingLot create(String parkingLotName,
//                             String address,
//                             int numberOfFloor,
//                             int entryGates,
//                             int exitGates,
//                             int spotPerFloor){
//        List<ParkingFloor> parkingFloors = new ArrayList<>();
//        for(int i=1;i<=numberOfFloor;i++){
//            parkingFloors.add(
//                    parkingFloorService.create(i, spotPerFloor, ParkingSpotType.NORMAL, VehicleType.FOUR_WHEELER)); // TODO -> accept spot Type and vehicle type
//        }
//
//        ParkingLot parkingLot = new ParkingLot();
//        parkingLot.setName(parkingLotName);
//        parkingLot.setAddress(address);
//        parkingLot.setParkingFloors(parkingFloors);
//
//        //TODO: gate creation is hardcoded, update the flow
//        ParkingGate entryGate = new ParkingGate();
//        entryGate.setGateNumber("Entry-1");
//        entryGate.setParkingGateType(ParkingGateType.ENTRY);
//
//        ParkingGate exitGate = new ParkingGate();
//        entryGate.setGateNumber("Exit-1");
//        entryGate.setParkingGateType(ParkingGateType.EXIT);
//
//        parkingLot.setParkingGates(List.of(entryGate, exitGate));
//        return parkingLotRepository.save(parkingLot);
//    }

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
}