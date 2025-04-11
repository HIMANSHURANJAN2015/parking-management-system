package util;

import model.ParkingFloor;
import model.ParkingLot;
import model.ParkingSpot;
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
        if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.FREE)){
            System.out.print("  |______________|  ");
        } else if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.OCCUPIED)){
            System.out.print(parkingSpot.getParkedVehicle().getNumber());
        } else {
            System.out.print("   |OUT-OF-ORDER|   ");
        }
    }
// ParkingLotService.displayParkingLot() -> ParkingLotUtils.print()
// displayParkingLot -> auth check, all empty check
}
