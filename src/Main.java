import controller.ParkingAttendantController;
import controller.ParkingLotController;
import repository.*;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Advaitya Parking Management System");

        ParkingSpotService  parkingSpotService = new ParkingSpotService(new ParkingSpotRepository());
        ParkingFloorService parkingFloorService = new ParkingFloorService(new ParkingFloorRepository());
        ParkingLotService parkingLotService = new ParkingLotService(new ParkingLotRepository());
        ParkingAttendantRepository parkingAttendantRepository = new ParkingAttendantRepository();
        ParkingGateService parkingGateService = new ParkingGateService(new ParkingGateRepository(), parkingAttendantRepository);
        ParkingAttendantService parkingAttendantService = new ParkingAttendantService(parkingAttendantRepository);
        ParkingAttendantController parkingAttendantController = new ParkingAttendantController(parkingAttendantService);
        ParkingLotController parkingLotController = new ParkingLotController(parkingLotService, parkingFloorService,
                parkingSpotService, parkingGateService, parkingAttendantController);

        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while(choice!=0) {
            System.out.println("Enter the choice code \n " +
                    "1 = Register a new Parking Lot(with all details) \n " +
                    "2 = Print a parking Lot \n " +
                    "0 = Exit");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice) {
                case 1: parkingLotController.create(); break;
                case 2: break;
                case 0: break;
                default: throw new IllegalArgumentException("Invalid choice");
            }

        }
    }
}