package controller;

import model.ParkingAttendant;
import repository.ParkingAttendantRepository;
import service.ParkingAttendantService;

import java.util.Scanner;

public class ParkingAttendantController {
    private ParkingAttendantService parkingAttendantService;
    private Scanner scanner;

    public ParkingAttendantController(ParkingAttendantService pas) {
        this.parkingAttendantService = pas;
        this.scanner = new Scanner(System.in);
    }

    public ParkingAttendant createParkingAttendant() {
        ParkingAttendant parkingAttendant = null;
        //Taking user input and validating them
        try {
            System.out.println("Enter ParkingAttendant name");
            String parkingAttendantName = scanner.nextLine();
            System.out.println("Enter ParkingAttendant email");
            String parkingAttendantEmail = scanner.nextLine();
            if(parkingAttendantName.isEmpty() || parkingAttendantEmail.isEmpty()) {
                throw new IllegalArgumentException("ParkingAttendant name or email is empty");
            }
            parkingAttendant = this.parkingAttendantService.create(parkingAttendantName, parkingAttendantEmail);
        } catch (Exception e) {
            System.out.println("OOPS!! Failed to create parking attendant. Error:"+e.getMessage());
        }
        return parkingAttendant;
    }
}
