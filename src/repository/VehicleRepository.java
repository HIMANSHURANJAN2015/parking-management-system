package repository;

import model.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<Long, Vehicle> vehicleMap = new HashMap<>();
    private static int id = 1;

    public Vehicle save(Vehicle vehicle){
        if(vehicle.getId() == 0) {
            vehicle.setId(id++);
        }
        vehicleMap.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber){
        for(Long vehicleId: vehicleMap.keySet()) {
            Vehicle vehicle = vehicleMap.get(vehicleId);
            if(vehicle.getNumber().equals(registrationNumber)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }
}
