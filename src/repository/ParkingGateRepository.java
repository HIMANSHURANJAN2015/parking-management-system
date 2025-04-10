package repository;

import exception.ParkingGateDoesNotExistException;
import model.ParkingAttendant;
import model.ParkingGate;
import model.constant.ParkingGateType;

import java.util.HashMap;
import java.util.Map;

public class ParkingGateRepository {
    private Map<Long, ParkingGate> parkingGateMap;
    private static int counter = 1;

    public ParkingGateRepository() {
        parkingGateMap = new HashMap<Long, ParkingGate>();
    }

    public ParkingGate save(ParkingGate parkingGate){
        parkingGate.setId(counter++);
        parkingGateMap.put(parkingGate.getId(), parkingGate);
        return parkingGate;
    }

    public ParkingGate findById(int parkingGateId){
        if(parkingGateMap.containsKey(parkingGateId)){
            return parkingGateMap.get(parkingGateId);
        } else {
            throw new ParkingGateDoesNotExistException("Parking Gate Does not Exist");
        }
    }
}
