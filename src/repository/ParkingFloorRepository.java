package repository;

import exception.ParkingFloorDoesNotExistException;
import exception.ParkingSpotDoesNotExistException;
import model.ParkingFloor;
import model.ParkingSpot;

import java.util.HashMap;
//TODO : make all repositories singleton -> repository or DAO[Data Access Object] are the same thing
public class ParkingFloorRepository {
    private HashMap<Long, ParkingFloor> parkingFloorMap;
    private static long id = 1;

    public ParkingFloorRepository() {
        this.parkingFloorMap = new HashMap<>();
    }

    public ParkingFloor save(ParkingFloor parkingFloor){
        parkingFloor.setId(id++);
        parkingFloorMap.put(parkingFloor.getId(), parkingFloor);
        return parkingFloorMap.get(parkingFloor.getId());
    }

    public ParkingFloor findById(long parkingFloorId){
        if(parkingFloorMap.containsKey(parkingFloorId)){
            return parkingFloorMap.get(parkingFloorId);
        } else {
            throw new ParkingFloorDoesNotExistException("Parking Floor Does not Exist");
        }
    }

    public ParkingFloor update(long parkingFloorId, ParkingFloor newParkingFloor){
        if(parkingFloorMap.containsKey(parkingFloorId)){
            return parkingFloorMap.put(parkingFloorId, newParkingFloor);
        } else {
            throw new ParkingFloorDoesNotExistException("Parking Floor Does not Exist");
        }
    }

    public void delete(long parkingFloorId){
        if(parkingFloorMap.containsKey(parkingFloorId)){
            parkingFloorMap.remove(parkingFloorId);
        } else {
            throw new ParkingFloorDoesNotExistException("Parking Floor Does not Exist");
        }
    }
}