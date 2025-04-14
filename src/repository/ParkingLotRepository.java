package repository;

import exception.ParkingLotDoesNotExistException;
import model.ParkingLot;
import model.ParkingGate;

import java.util.HashMap;
import java.util.Optional;
import java.util.List;

//TODO : make all repositories singleton -> repository or DAO[Data Access Object] are the same thing
public class ParkingLotRepository {
    private HashMap<Long, ParkingLot> parkingLotMap;
    private static long id = 1;

    public ParkingLotRepository() {
        this.parkingLotMap = new HashMap<>();
    }

    public ParkingLot save(ParkingLot parkingLot){
        parkingLot.setId(id++);
        parkingLotMap.put(parkingLot.getId(), parkingLot);
        return parkingLotMap.get(parkingLot.getId());
    }

    public Optional<ParkingLot> findById(long id){
        return Optional.ofNullable(this.parkingLotMap.get(id));
    }

    public Optional<ParkingLot> finByGateId(long gateId){
        for(Long key: parkingLotMap.keySet()) {
            ParkingLot parkingLot = this.parkingLotMap.get(key);
            List<ParkingGate> gates = parkingLot.getParkingGates();
            if(isGateIdPresent(gates, gateId)) {
                return Optional.of(parkingLot);
            }
        }
        return Optional.empty();
    }

    private boolean isGateIdPresent(List<ParkingGate> gates, long gateId) {
        for(ParkingGate gate: gates) {
            if(gate.getId() == gateId) {
                return true;
            }
        }
        return false;
    }

    public ParkingLot update(long parkingLotId, ParkingLot newParkingLot){
        if(parkingLotMap.containsKey(parkingLotId)){
            return parkingLotMap.put(parkingLotId, newParkingLot);
        } else {
            throw new ParkingLotDoesNotExistException("Parking Lot Does not Exist");
        }
    }

    public void delete(long parkingLotId){
        if(parkingLotMap.containsKey(parkingLotId)){
            parkingLotMap.remove(parkingLotId);
        } else {
            throw new ParkingLotDoesNotExistException("Parking Lot Does not Exist");
        }
    }
}