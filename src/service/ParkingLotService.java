package service;

import exception.ParkingLotDoesNotExistException;
import model.ParkingFloor;
import model.ParkingGate;
import model.ParkingLot;
import model.ParkingSpot;
import model.constant.*;
import repository.ParkingLotRepository;
import util.ParkingLotUtils;

import java.util.*;

public class ParkingLotService {

    private ParkingLotRepository parkingLotRepo;
    private ParkingLotUtils parkingLotUtils;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotUtils parkingLotUtils) {
        this.parkingLotRepo = parkingLotRepository;
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
        return parkingLotRepo.save(parkingLot);
    }

    public void printParkingLot(long parkingLotId) {
        Optional<ParkingLot> parkingLotOpt = parkingLotRepo.findById(parkingLotId);
        if(parkingLotOpt.isEmpty()) {
            throw new ParkingLotDoesNotExistException("Parking Lot Not found");
        }
        ParkingLotUtils.print(parkingLotOpt.get());
    }

    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloorIds, List<VehicleType> vehicleTypes) throws ParkingLotDoesNotExistException {
        Optional<ParkingLot> parkingLotOpt = this.parkingLotRepo.findById(parkingLotId);
        if(parkingLotOpt.isEmpty()) {
            throw new ParkingLotDoesNotExistException("ParkingLot with id= "+ parkingLotId+ " not found");
        }

        //now filter basd on parking flooors and vehicle types
        boolean floorFilterAvailable = false;
        boolean vehicleTypeFilterAvailable = false;
        if(parkingFloorIds.size()> 0) {
            floorFilterAvailable = true;
        }
        if(vehicleTypes.size()> 0) { //i gave it >= her and above and was failign test cases
            vehicleTypeFilterAvailable = true;
        }
        Map<ParkingFloor, Map<String, Integer>> capacityMap = new HashMap<>();
        List<ParkingFloor> parkingfloors = parkingLotOpt.get().getParkingFloors();
        for(ParkingFloor floor: parkingfloors) {
            if((floor.getFloorStatus() != ParkingFloorStatus.OPERATIONAL) ||
                    (floorFilterAvailable && !isPresent(parkingFloorIds, floor.getId()))){
                continue;
            }
            HashMap<String, Integer> capacityMapOnThisFloor = new HashMap<>();
            for(ParkingSpot spot: floor.getParkingSpots()) {
                if((spot.getParkingSpotStatus() != ParkingSpotStatus.AVAILABLE) ||
                        (vehicleTypeFilterAvailable && !isPresent(vehicleTypes, spot.getVehicleType()))
                ){
                    continue;
                }
                String spotVehicleType = spot.getVehicleType().toString();
                capacityMapOnThisFloor.put(spotVehicleType, capacityMapOnThisFloor.getOrDefault(spotVehicleType, 0)+1);
            }
            capacityMap.put(floor, capacityMapOnThisFloor);
        }
        return capacityMap;
    }

    private boolean isPresent(List<Long> parkingFloorIds, Long key) {
        for(long i: parkingFloorIds) {
            if(i==key) {
                return true;
            }
        }
        return false;
    }

    private boolean isPresent(List<VehicleType> vehicleTypes, VehicleType type) {
        for(VehicleType i: vehicleTypes) {
            if(i.equals(type)) {
                return true;
            }
        }
        return false;
    }
}