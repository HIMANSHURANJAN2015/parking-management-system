package service;

import model.ParkingAttendant;
import repository.ParkingAttendantRepository;

public class ParkingAttendantService {

    private ParkingAttendantRepository parkingAttendantRepo;

    public ParkingAttendantService(ParkingAttendantRepository parkingAttendantRepo) {
        this.parkingAttendantRepo = parkingAttendantRepo;
    }

    public ParkingAttendant create(String name, String email) {
        ParkingAttendant parkingAttendant = new ParkingAttendant();
        parkingAttendant.setName(name);
        parkingAttendant.setEmail(email);
        return parkingAttendantRepo.save(parkingAttendant);
    }
}
