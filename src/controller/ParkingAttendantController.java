package controller;

import model.ParkingAttendant;
import repository.ParkingAttendantRepository;
import service.ParkingAttendantService;
import util.InputUtils;
import util.ParkingLotUtils;

import java.util.Scanner;

public class ParkingAttendantController {
    private ParkingAttendantService parkingAttendantService;
    private Scanner scanner;

    public ParkingAttendantController(ParkingAttendantService pas) {
        this.parkingAttendantService = pas;
        this.scanner = new Scanner(System.in);
    }

    public void startFileReadingMode(Scanner sc) {
        //this.scanner.close();
        this.scanner = sc;
    }

    public void stopFileReadingMode() {
        //this.scanner.close();
        this.scanner = new Scanner(System.in);
    }

    public ParkingAttendant createParkingAttendant() {
        ParkingAttendant parkingAttendant = null;
        //Taking user input and validating them
        try {
            String parkingAttendantName = InputUtils.getValidString(scanner, "Enter ParkingAttendant name");
            String parkingAttendantEmail = InputUtils.getValidString(scanner, "Enter ParkingAttendant email");
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
