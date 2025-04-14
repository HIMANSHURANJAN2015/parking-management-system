package util;

import model.*;
import model.constant.ParkingSpotStatus;

import java.util.List;
import java.util.Scanner;

public class ParkingLotUtils {
    public static void print(ParkingLot parkingLot){
        System.out.println("------------------------------------------------------------------------ 000 ------------------------------------------------------------------------");
        System.out.println("PARKINGLOT ID  : " + parkingLot.getId());
        System.out.println("PARKINGLOT NAME: " + parkingLot.getName());
        System.out.println("Here is the current status of the parking lot:");
        List<ParkingFloor> parkingFloors = parkingLot.getParkingFloors();
        for (ParkingFloor parkingFloor : parkingFloors) {
            System.out.print("FLOOR "+parkingFloor.getFloorNumber()+"           ");
            List<ParkingSpot> parkingSpots = parkingFloor.getParkingSpots();
            for (ParkingSpot parkingSpot : parkingSpots) {
                print(parkingSpot);
            }
            System.out.println();
        }
        System.out.println("\n\n------------------------------------------------------------------------ XXX ------------------------------------------------------------------------");
    }

    public static void print(ParkingFloor parkingFloor){

    }

    public static void print(ParkingSpot parkingSpot){
        if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)){
            System.out.print("  |______________|  ");
        } else if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.OCCUPIED)){
            System.out.printf("  |   %-8s   |  ", parkingSpot.getParkedVehicle().getNumber());
        } else {
            System.out.print("  |OUT-OF-ORDER|  ");
        }
    }
// ParkingLotService.displayParkingLot() -> ParkingLotUtils.print()
// displayParkingLot -> auth check, all empty check

    public static void print(ParkingTicket ticket) {
        String border = "|------------------------------------------------------------------------------|";
        String emptyLine = "|                                                                              |";

        System.out.println(border); // Top border
        System.out.println(emptyLine);
        System.out.println("|   Ticket successfully generated. Here are the details                        |");
        System.out.println(emptyLine);
        System.out.println(border); // Divider border
        System.out.printf("|   %-74s   |\n", "Ticket id: " + ticket.getId());
        System.out.printf("|   %-74s   |\n", "Parking spot: " + ticket.getParkingSpot().getParkingSpotNumber());
        System.out.printf("|   %-74s   |\n", "Entry time: " + ticket.getEntryTime());
        System.out.printf("|   %-74s   |\n", "Vehicle number: " + ticket.getVehicle().getNumber());
        System.out.printf("|   %-74s   |\n", "Gate number: " + ticket.getEntryGate().getGateName());
        System.out.printf("|   %-74s   |\n", "Parking Attendant Name: " + ticket.getParkingAttendant().getName());
        if (ticket.getAdditionalServices() != null) {
            System.out.printf("|   %-74s   |\n", "Additional Services Opted: " + ticket.getAdditionalServices().toString());
        } else {
            System.out.println("No additional services opted.");
        }
        System.out.println(border); // Bottom border
    }

    public static void print(Invoice invoice) {
        String border = "|------------------------------------------------------------------------------|";
        String emptyLine = "|                                                                              |";

        System.out.println(border); // Top border
        System.out.println(emptyLine);
        System.out.println("|   Invoice successfully generated. Here are the details:                        |");
        System.out.println(emptyLine);
        System.out.println(border); // Divider border
        System.out.printf("|   %-74s   |\n", "Invoice id: " + invoice.getId());
        System.out.printf("|   %-74s   |\n", "Ticket id: " + invoice.getParkingTicket().getId());
        System.out.printf("|   %-74s   |\n", "Exit Gate number: " + invoice.getExitGate().getGateName());
        System.out.printf("|   %-74s   |\n", "Parking Attendant Name: " + invoice.getAttendant().getName());
        System.out.printf("|   %-74s   |\n", "Exit time: " + invoice.getExitTime());
        System.out.printf("|   %-74s   |\n", "Final Payable amount: " + invoice.getAmount());
        System.out.println(border); // Bottom border
    }
}
