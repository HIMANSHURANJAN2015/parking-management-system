package repository;

import exception.ParkingLotDoesNotExistException;
import model.ParkingLot;

import java.util.HashMap;
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

    public ParkingLot findById(long parkingLotId){
        if(parkingLotMap.containsKey(parkingLotId)){
            return parkingLotMap.get(parkingLotId);
        } else {
            throw new ParkingLotDoesNotExistException("Parking Lot Does not Exist");
        }
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