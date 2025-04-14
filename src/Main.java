import controller.ParkingAttendantController;
import controller.ParkingLotController;
import controller.ParkingTicketController;
import repository.*;
import service.*;
import service.strategy.spotassignment.NearestSpotAssignmentStrategy;
import util.ParkingLotUtils;
import util.InputUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Advaitya Parking Management System");

        //Creating ParkingLot Controller
        ParkingSpotService  parkingSpotService = new ParkingSpotService(new ParkingSpotRepository());
        ParkingFloorService parkingFloorService = new ParkingFloorService(new ParkingFloorRepository());
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository, new ParkingLotUtils());
        ParkingAttendantRepository parkingAttendantRepository = new ParkingAttendantRepository();
        ParkingGateRepository parkingGateRepository = new ParkingGateRepository();
        ParkingGateService parkingGateService = new ParkingGateService(parkingGateRepository, parkingAttendantRepository);
        ParkingAttendantService parkingAttendantService = new ParkingAttendantService(parkingAttendantRepository);
        ParkingAttendantController parkingAttendantController = new ParkingAttendantController(parkingAttendantService);
        ParkingLotController parkingLotController = new ParkingLotController(parkingLotService, parkingFloorService,
                parkingSpotService, parkingGateService, parkingAttendantController);

        //Creating ParkingTicket Controller
        ParkingTicketService parkingTicketService = new ParkingTicketService(new ParkingTicketRepository(), parkingGateRepository,
                parkingLotRepository, new VehicleRepository(), new NearestSpotAssignmentStrategy());
        ParkingTicketController parkingTicketController = new ParkingTicketController(parkingTicketService);

        Scanner sc = new Scanner(System.in);
        int choice = 1;
        try {
            while (choice != 0) {
                choice = InputUtils.getValidInt(sc, "Enter the choice code \n " +
                        "1 = Register a new Parking Lot(with all details) \n " +
                        "2 = Auto-register a new Parking Lot(with dummy data) \n " +
                        "3 = Print a parking Lot \n " +
                        "4 = Get Parking lot capacity \n " +
                        "5 = Generate Parking ticket \n " +
                        "0 = Exit");
                switch (choice) {
                    case 1:
                        parkingLotController.create();
                        break;
                    case 2:
                        // Asking controllers to Switch to reading from dummy.txt
                        Scanner fileSc = new Scanner(new File("DummyParkingLotData.txt"));
                        parkingLotController.startFileReadingMode(fileSc);
                        parkingAttendantController.startFileReadingMode(fileSc);
                        //Now calling controller
                        parkingLotController.create();
                        break;
                    case 3:
                        parkingLotController.print();
                        break;
                    case 4:
                        parkingLotController.getParkingLotCapacity();
                        break;
                    case 5:
                        parkingTicketController.generateTicket();
                        break;
                    case 0:
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid choice");
                }
                // Asking controllers to stop reading from dummy.txt
                parkingLotController.stopFileReadingMode();
                parkingAttendantController.stopFileReadingMode();
            }
            System.out.println("Thank you for using Advaitya Parking Management System");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/* LEarning about reading from file
1. Keep file at project directory outside src since it directly read from there.
    Try to know current workign directoy of java compiler
    System.out.println("Working Directory: " + System.getProperty("user.dir"));
2. Dont close scanner object in controllers in stopFileReadingMode() because it qas causing me NoSuchElemewntException
    whihc generally heppens when there are nothing more to read or when we tend to read after scanner is closed

 */