package service;

import java.util.*;
import model.*;
import model.constant.AdditionalService;
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

    public ParkingTicket generateTicket(long gateId, String registrationNumber, String vehicleType, List<String> additionalServicesList) throws
                                ParkingGateDoesNotExistException, InvalidGateException,
                                ParkingLotDoesNotExistException, ParkingSpotNotAvailableException,
                                UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle  {
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

        //Transforming list of additional services, if it exists
        List<AdditionalService> chosenServices = new ArrayList<>();
        if(additionalServicesList != null) {
            //if its null, then I cannot invoke .size() method
            chosenServices = this.tranform(additionalServicesList, VehicleType.valueOf(vehicleType));
        }

        //now with abve 3 things, generating ticket
        ParkingTicket ticket = new ParkingTicket();
        ticket.setParkingSpot(parkingSpotOpt.get());
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicle);
        ticket.setEntryGate(gate);
        ticket.setParkingAttendant(gate.getParkingAttendant());
        ticket.setAdditionalServices(chosenServices);

        return this.ticketRepo.save(ticket);
    }

    private List<AdditionalService> tranform(List<String> servicesAsString, VehicleType vehicleType) throws UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle {
        List<AdditionalService> servicesAsAdditonalService = new ArrayList<>();
        for(String serviceIn: servicesAsString) {
            try {
                AdditionalService serviceOut = AdditionalService.valueOf(serviceIn);
                List<VehicleType> supportedVehicles = serviceOut.getSupportedVehicleTypes();
                if(!supportedVehicles.contains(vehicleType)){
                    throw new AdditionalServiceNotSupportedByVehicle("Additional service : "+serviceOut.name()+ " not supported for vehicle type:"+vehicleType.name());
                }
                servicesAsAdditonalService.add(serviceOut);
            } catch(IllegalArgumentException e) {
                throw new UnsupportedAdditionalService("Additional service "+ serviceIn+ " not supported");
            }
        }
        return servicesAsAdditonalService;
        /*
         * If i dont want to handle the exception, then no need of try catch block but method signature must declare the thorws clause
         * but in this case i need to change IllegalArgumentException to  UnsupportedAdditionalService exception, so i am doing tray-cath
         *
         * Further i dont even want to hanlde in service method so i am not using try cath in service
         *
         * But i want to handle exception in ctolerller . so controller has try cath
         */
    }
}
