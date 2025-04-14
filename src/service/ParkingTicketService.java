package service;

import java.util.*;
import model.*;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotStatus;
import model.constant.VehicleType;
import repository.*;
import service.strategy.spotassignment.*;
import exception.*;


public class ParkingTicketService {
    private ParkingTicketRepository ticketRepo;
    private ParkingGateRepository gateRepo;
    private ParkingLotRepository parkingLotRepo;
    private VehicleRepository vehicleRepo;
    private SpotAssignmentStrategy spotAssignmentStrategy;

    public ParkingTicketService(ParkingTicketRepository t, ParkingGateRepository g, ParkingLotRepository p, VehicleRepository v, SpotAssignmentStrategy s) {
        this.ticketRepo = t;
        this.gateRepo = g;
        this.parkingLotRepo = p;
        this.vehicleRepo = v;
        this.spotAssignmentStrategy = s;
    }

    public ParkingTicket generateTicket(long gateId, String registrationNumber, String vehicleType) throws ParkingGateDoesNotExistException, InvalidGateException, ParkingLotDoesNotExistException, ParkingSpotNotAvailableException {
        //Checking gateId and fecthing Gate object
        Optional<ParkingGate> gateOpt = this.gateRepo.findById(gateId);
        if(gateOpt.isEmpty()) {
            throw new ParkingGateDoesNotExistException("Gate not found");
        }
        ParkingGate gate = gateOpt.get();
        if(gate.getParkingGateType() != ParkingGateType.ENTRY) {
            throw new InvalidGateException("Ticket cannot be generated at Exit gate");
        }

        //Fetching ParkingLot object
        Optional<ParkingLot> parkingLotOpt = this.parkingLotRepo.findByGateId(gateId);
        if(parkingLotOpt.isEmpty()) {
            throw new ParkingLotDoesNotExistException("Parkling lot not found");
        }
        ParkingLot parkingLot = parkingLotOpt.get();

        //Checking and creating vehicle, if not found. And fecthing Vehicle object
        Optional<Vehicle> vehicleOpt = this.vehicleRepo.getVehicleByRegistrationNumber(registrationNumber);
        Vehicle vehicle = null;
        if(vehicleOpt.isEmpty()) {
            vehicle = new Vehicle();
            vehicle.setNumber(registrationNumber);
            vehicle.setVehicleType(VehicleType.valueOf(vehicleType));
            vehicle = this.vehicleRepo.save(vehicle);
        } else {
            vehicle = vehicleOpt.get();
        }

        //now assigning ParkingSpot to the vehicle
        Optional<ParkingSpot> parkingSpotOpt = this.spotAssignmentStrategy.assignSpot(parkingLot, VehicleType.valueOf(vehicleType));
        if(parkingSpotOpt.isEmpty()) {
            throw new ParkingSpotNotAvailableException("OOPS!! No availabe parking space. Kindly Wait");
        }
        ParkingSpot parkingSpot = parkingSpotOpt.get();
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
        parkingSpot.setParkedVehicle(vehicle);//I forgot this and my test cases failed

        //now with abve 3 things, generating ticket
        ParkingTicket ticket = new ParkingTicket();
        ticket.setParkingSpot(parkingSpotOpt.get());
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicle);
        ticket.setEntryGate(gate);
        ticket.setParkingAttendant(gate.getParkingAttendant());

        return this.ticketRepo.save(ticket);
    }
}
