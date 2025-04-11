package repository;

import exception.ParkingSpotDoesNotExistException;
import model.ParkingSpot;

import java.util.HashMap;
//TODO : make all repositories singleton -> repository or DAO[Data Access Object] are the same thing
public class ParkingSpotRepository {
    private HashMap<Long, ParkingSpot> parkingSpotMap;
    private static long id = 1;

    public ParkingSpotRepository() {
        this.parkingSpotMap = new HashMap<>();
    }

    public ParkingSpot save(ParkingSpot parkingSpot){
        parkingSpot.setId(id++);
        parkingSpotMap.put(parkingSpot.getId(), parkingSpot);
        return parkingSpotMap.get(parkingSpot.getId());
    }

    public ParkingSpot findById(long parkingSpotId){
        if(parkingSpotMap.containsKey(parkingSpotId)){
            return parkingSpotMap.get(parkingSpotId);
        } else {
            throw new ParkingSpotDoesNotExistException("Parking Spot Does not Exist");
        }
    }

    public ParkingSpot update(long parkingSpotId, ParkingSpot newParkingSpot){
        if(parkingSpotMap.containsKey(parkingSpotId)){
            return parkingSpotMap.put(parkingSpotId, newParkingSpot);
        } else {
            throw new ParkingSpotDoesNotExistException("Parking Spot Does not Exist");
        }
    }

    public void delete(long parkingSpotId){
        if(parkingSpotMap.containsKey(parkingSpotId)){
            parkingSpotMap.remove(parkingSpotId);
        } else {
            throw new ParkingSpotDoesNotExistException("Parking Spot Does not Exist");
        }
    }
}