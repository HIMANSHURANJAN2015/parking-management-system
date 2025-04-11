package repository;

import exception.ParkingAttendantDoesNotExistException;
import model.ParkingAttendant;

import java.util.HashMap;

public class ParkingAttendantRepository {
    private HashMap<Long, ParkingAttendant> parkingAttendantMap;
    private static long id = 1;

    public ParkingAttendantRepository() {
        this.parkingAttendantMap = new HashMap<>();
    }

    public ParkingAttendant save(ParkingAttendant parkingAttendant){
        parkingAttendant.setId(id++);
        parkingAttendantMap.put(parkingAttendant.getId(), parkingAttendant);
        return parkingAttendantMap.get(parkingAttendant.getId());
    }

    public ParkingAttendant findById(long parkingAttendantId){
        if(parkingAttendantMap.containsKey(parkingAttendantId)){
            return parkingAttendantMap.get(parkingAttendantId);
        } else {
            throw new ParkingAttendantDoesNotExistException("Parking Attendant Does not Exist");
        }
    }

    public ParkingAttendant update(long parkingAttendantId, ParkingAttendant newParkingAttendant){
        if(parkingAttendantMap.containsKey(parkingAttendantId)){
            return parkingAttendantMap.put(parkingAttendantId, newParkingAttendant);
        } else {
            throw new ParkingAttendantDoesNotExistException("Parking Attendant Does not Exist");
        }
    }

    public void delete(long parkingAttendantId){
        if(parkingAttendantMap.containsKey(parkingAttendantId)){
            parkingAttendantMap.remove(parkingAttendantId);
        } else {
            throw new ParkingAttendantDoesNotExistException("Parking Attendant Does not Exist");
        }
    }
}