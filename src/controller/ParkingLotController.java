package controller;

import model.ParkingFloor;
import model.ParkingGate;
import model.ParkingLot;
import model.ParkingSpot;
import model.constant.ParkingGateType;
import model.constant.ParkingSpotType;
import model.constant.VehicleType;
import service.*;
import util.InputUtils;

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

    public void startFileReadingMode(Scanner sc) {
        //this.sc.close(); dont close since I am getting error, NoSuchElementException
        this.sc = sc;
    }

    public void stopFileReadingMode() {
        //this.sc.close();
        this.sc = new Scanner(System.in);
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
            String parkingLotName = InputUtils.getValidString(sc, "Enter Parking Lot name");
            String parkingLotAddress = InputUtils.getValidString(sc, "Enter Parking Lot address");
            int numberOfFloors = InputUtils.getValidInt(sc, "Enter Number of Floors");
            if(numberOfFloors < 0) {
                throw new IllegalArgumentException("Number of floors must be greater than 0");
            }
            int numberOfEntryGates = InputUtils.getValidInt(sc, "Enter Number of Entry Gates");
            int numberOfExitGates = InputUtils.getValidInt(sc, "Enter Number of Exit Gates");
            if(numberOfEntryGates < 1 || numberOfExitGates < 1 ) {
                throw new IllegalArgumentException("Atleast 1 entry gate and 1 exit gate must be there");
            }

            //Taking parking spot inputs and creating parking floors
            List<ParkingFloor> parkingFloorList = new ArrayList<ParkingFloor>();
            for(int floorNumber = 1; floorNumber <= numberOfFloors; floorNumber++) {
                int numberOfSpots = InputUtils.getValidInt(sc, "Enter Number of parking spots on this floor : "+floorNumber);
                List<ParkingSpot> parkingSpotList = new ArrayList<>();
                for(int j=1; j<=numberOfSpots; j++) {
                    int spotNumber = 100*floorNumber + j;
                    ParkingSpotType type = InputUtils.getValidParkingSpotType(sc, "Enter spot type of spot number : "+(j));
                    System.out.println();
                    VehicleType vehicleType = InputUtils.getValidVehicleType(sc, "Enter vehicle type of spot number : "+(j));
                    parkingSpotList.add(parkingSpotService.create(spotNumber, type, vehicleType));
                }
                parkingFloorList.add(parkingFloorService.create(floorNumber, parkingSpotList));
            }

            //Taking parking attendant inputs
            int numberOfUsers = InputUtils.getValidInt(sc, "Enter number of parking attendant you want to add. For a new parking lot, \n " +
                    "at least 2 user must be added, 1 for entry and 1 for exit");
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
                String gateName = InputUtils.getValidString(sc, "Enter Gate name e.g. ENTRY-1");
                int gateAttendantId = InputUtils.getValidInt(sc, "Enter Gate attendant id");
                parkingGateList.add(parkingGateService.create(gateName, ParkingGateType.ENTRY, gateAttendantId));
            }
            System.out.println("Now, Enter details about exit gates");
            for(int gateNumber = 1; gateNumber <= numberOfExitGates; gateNumber++) {
                String gateName = InputUtils.getValidString(sc, "Enter Gate name e.g. EXIT-1");
                int gateAttendantId = InputUtils.getValidInt(sc, "Enter Gate attendant id");
                parkingGateList.add(parkingGateService.create(gateName, ParkingGateType.EXIT, gateAttendantId));
            }

            //Finally, creating parking lot object
            parkingLot = parkingLotService.create(parkingLotName, parkingLotAddress, parkingFloorList, parkingGateList);
            System.out.println("New parking lot registered successfully. Kindly note down the id: "+parkingLot.getId());
            parkingLotService.printParkingLot(parkingLot.getId());
        } catch(Exception e) {
            System.out.println("OOPS!! Parking Lot not created. Error:"+e.getMessage());
        }
        return parkingLot;
    }

    public void print(){
        try {
            int parkingLotId = InputUtils.getValidInt(sc, "Enter parking lot id");
            parkingLotService.printParkingLot(parkingLotId);

        } catch(Exception e) {
            System.out.println("OOPS!! Unable to display parking lot. Error:"+e.getMessage());
        }
    }

}