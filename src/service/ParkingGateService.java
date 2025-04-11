package service;

import model.ParkingAttendant;
import model.ParkingGate;
import model.constant.ParkingGateType;
import repository.ParkingAttendantRepository;
import repository.ParkingGateRepository;

public class ParkingGateService {
    private ParkingGateRepository parkingGateRepo;
    private ParkingAttendantRepository parkingAttendantRepo;

    public ParkingGateService(ParkingGateRepository parkingGateRepo, ParkingAttendantRepository parkingAttendantRepo) {
        this.parkingGateRepo = parkingGateRepo;
        this.parkingAttendantRepo = parkingAttendantRepo;
    }

    public ParkingGate create(String gateName, ParkingGateType gateType, long attendantId) {
        ParkingAttendant attendant = parkingAttendantRepo.findById(attendantId);
        ParkingGate parkingGate = new ParkingGate();
        parkingGate.setGateName(gateName);
        parkingGate.setParkingGateType(gateType);
        parkingGate.setParkingAttendant(attendant);
        return parkingGateRepo.save(parkingGate);
    }
}
