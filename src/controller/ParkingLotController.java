package controller;

import model.ParkingFloor;
import model.ParkingGate;
import model.ParkingLot;
import model.ParkingSpot;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotController {

    private ParkingLotService parkingLotService;
    private ParkingFloorService parkingFloorService;
    private ParkingSpotService parkingSpotService;
    private ParkingGateService parkingGateService;
    private ParkingAttendantController parkingAttendantController;

    Scanner sc;

    public ParkingLotController(ParkingLotService parkingLotService, ParkingFloorService parkingFloorService,
                                ParkingSpotService parkingSpotService, ParkingGateService parkingGateService,
                                ParkingAttendantController parkingAttendantController) {
        this.parkingLotService = parkingLotService;
        this.parkingFloorService = parkingFloorService;
        this.parkingSpotService = parkingSpotService;
        this.parkingGateService = parkingGateService;
        this.parkingAttendantController = parkingAttendantController;
        sc = new Scanner(System.in);
    }

    public ParkingLot create(){
         /*
        As its console based application, so  create() method in the controller doesn't need
        parameters—it can simply ask for user inputs via the console, validate them, and pass
        the data (either as individual parameters or encapsulated in a model/DTO) to the service layer.
          */
        ParkingLot parkingLot = null;
        //taking input from user and validating them
        try {
            //Taking basic parking lot inputs
            System.out.println("Enter Parking Lot name");
            String parkingLotName = sc.nextLine();
            System.out.println("Enter Parking Lot address");
            String parkingLotAddress = sc.nextLine();
            System.out.println("Enter number of floors in your parking lot");
            int numberOfFloors = Integer.parseInt(sc.nextLine());
            if(numberOfFloors < 0) {
                throw new IllegalArgumentException("Number of floors must be greater than 0");
            }
            System.out.println("Enter number of entry gates in your parking lot");
            int numberOfEntryGates = Integer.parseInt(sc.nextLine());
            System.out.println("Enter number of exit gates in your parking lot");
            int numberOfExitGates = Integer.parseInt(sc.nextLine());
            if(numberOfEntryGates < 1 || numberOfExitGates < 1 ) {
                throw new IllegalArgumentException("Atleast 1 entry gate and 1 exit gate must be there");
            }

            //Taking parking spot inputs and creating parking floors
            List<ParkingFloor> parkingFloorList = new ArrayList<ParkingFloor>();
            for(int floorNumber = 1; floorNumber <= numberOfFloors; floorNumber++) {
                System.out.println("Enter number of parking spots on this floor : "+floorNumber);
                int numberOfSlots = Integer.parseInt(sc.nextLine());
                List<ParkingSpot> parkingSpotList = new ArrayList<>();
                for(int j=1; j<=numberOfSlots; j++) {
                    int spotNumber = 100*floorNumber + j;
                    System.out.println("Enter spot type of spot number : "+(j));
                    ParkingSpotType type = ParkingSpotType.valueOf(sc.nextLine());
                    System.out.println("Enter vehicle type of spot number : "+(j));
                    VehicleType vehicleType = VehicleType.valueOf(sc.nextLine());
                    parkingSpotList.add(parkingSpotService.create(spotNumber, type, vehicleType));
                }
                parkingFloorList.add(parkingFloorService.create(floorNumber, parkingSpotList));
            }

            //Taking parking attendant inputs
            System.out.println("Enter number of parking attendant you want to add. For a new parking lot, \n " +
                    "at least 2 user must be added, 1 for entry and 1 for exit");
            int numberOfUsers = Integer.parseInt(sc.nextLine());
            if(numberOfUsers < 2) {
                throw new IllegalArgumentException("Number of users must be greater than 2, 1 for entry and 1 for exit");
            }
            for(int userNumber = 1; userNumber<= numberOfUsers; userNumber++) {
                this.parkingAttendantController.createParkingAttendant();
            }


            //Taking parking lot gates inputs and creating parking gate
            List<ParkingGate> parkingGateList = new ArrayList<>();
            System.out.println("Enter details about entry gates");
            for(int gateNumber = 1; gateNumber <= numberOfEntryGates; gateNumber++) {
                System.out.println("Enter Gate name e.g. ENTRY-1");
                String gateName = sc.nextLine();
                System.out.println("Enter Gate attendant id");
                int gateAttendantId = Integer.parseInt(sc.nextLine());
                parkingGateList.add(parkingGateService.create(gateName, ParkingGateType.ENTRY, gateAttendantId));
            }
            System.out.println("Now, Enter details about exit gates");
            for(int gateNumber = 1; gateNumber <= numberOfExitGates; gateNumber++) {
                System.out.println("Enter Gate name e.g. EXIT-1");
                String gateName = sc.nextLine();
                System.out.println("Enter Gate attendant id");
                int gateAttendantId = Integer.parseInt(sc.nextLine());
                parkingGateList.add(parkingGateService.create(gateName, ParkingGateType.EXIT, gateAttendantId));
            }

            //Finally, creating parking lot object
            parkingLot = parkingLotService.create(parkingLotName, parkingLotAddress, parkingFloorList, parkingGateList);
            System.out.println("New parking lot registered successfully. Kindly note down the id: "+parkingLot.getId());
        } catch(Exception e) {
            System.out.println("OOPS!! Parking Lot not created. Error:"+e.getMessage());
        }
        return parkingLot;
    }
}